package com.soebes.maven.extensions;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.maven.eventspy.AbstractEventSpy;
import org.apache.maven.execution.MavenExecutionResult;
import org.eclipse.aether.RepositoryEvent;
import org.eclipse.aether.RepositoryEvent.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Singleton;

/**
 * @author Karl Heinz Marbaise <khmarbaise@apache.org>
 */
@Named
@Singleton
public class ArtifactCollector
    extends AbstractEventSpy
{
    private final Logger LOGGER = LoggerFactory.getLogger( getClass() );

    private List<String> collectedArtifacts = Collections.synchronizedList( new LinkedList<String>() );

    @Inject
    public ArtifactCollector()
    {
        LOGGER.debug( "ArtifactCollector ctor called." );
    }

    @Override
    public void init( Context context )
        throws Exception
    {
        super.init( context );

        LOGGER.info( "Maven Artifact Collector Version {} started.", ArtifactCollectorVersion.getVersion() );

    }

    @Override
    public void onEvent( Object event )
        throws Exception
    {
        try
        {
            if ( event instanceof org.eclipse.aether.RepositoryEvent )
            {
                repositoryEventHandler( (RepositoryEvent) event );
            }
            else if ( event instanceof MavenExecutionResult )
            {
                executionResultEventHandler( (MavenExecutionResult) event );
            }
            else
            {
                // TODO: What kind of event we haven't considered?
                LOGGER.debug( "Event {}", event.getClass().getCanonicalName() );
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception", e );
        }
    }

    @Override
    public void close()
    {
        LOGGER.debug( "done." );
    }

    private void repositoryEventHandler( org.eclipse.aether.RepositoryEvent repositoryEvent )
    {
        EventType type = repositoryEvent.getType();
        switch ( type )
        {
            case ARTIFACT_INSTALLED:
                collectedArtifacts.add( ArtifactFormatter.getGAVFromArtifact( repositoryEvent.getArtifact() ) );
                break;

            case ARTIFACT_DESCRIPTOR_INVALID:
            case ARTIFACT_DESCRIPTOR_MISSING:
            case METADATA_INVALID:
            case ARTIFACT_RESOLVING:
            case ARTIFACT_RESOLVED:
            case METADATA_RESOLVING:
            case METADATA_RESOLVED:
            case ARTIFACT_DOWNLOADING:
            case ARTIFACT_DOWNLOADED:
            case METADATA_DOWNLOADING:
            case METADATA_DOWNLOADED:
            case ARTIFACT_INSTALLING:
            case METADATA_INSTALLING:
            case METADATA_INSTALLED:
            case ARTIFACT_DEPLOYING:
            case ARTIFACT_DEPLOYED:
            case METADATA_DEPLOYING:
            case METADATA_DEPLOYED:
                // Those events are not recorded.
                break;

            default:
                LOGGER.error( "repositoryEventHandler {}", type );
                break;
        }
    }

    private void executionResultEventHandler( MavenExecutionResult event )
    {
        if (collectedArtifacts.isEmpty()) {
            return;
        }
            
        // TODO: Use better formatting
        LOGGER.info( "--             Maven Artifact Collector Summary                       --" );
        LOGGER.info( "------------------------------------------------------------------------" );
        for ( String item : collectedArtifacts )
        {
            LOGGER.info( item );
        }

    }

}
