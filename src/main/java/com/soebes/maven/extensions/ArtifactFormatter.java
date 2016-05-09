package com.soebes.maven.extensions;

import org.eclipse.aether.artifact.Artifact;

public class ArtifactFormatter
{
    public static String getGAVFromArtifact( Artifact artifact )
    {
        StringBuilder sb = new StringBuilder( artifact.getGroupId() );
        sb.append( ":" );
        sb.append( artifact.getArtifactId() );
        sb.append( ":" );
        sb.append( artifact.getVersion() );
        sb.append( ":" );
        sb.append( artifact.getExtension() );
        if ( artifact.getClassifier() != null && !artifact.getClassifier().isEmpty() )
        {
            sb.append( ":" );
            sb.append( artifact.getClassifier() );
        }
        return sb.toString();
    }

}
