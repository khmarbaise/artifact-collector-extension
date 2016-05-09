package com.soebes.maven.extensions;

import static com.soebes.maven.extensions.ArtifactFormatter.getGAVFromArtifact;
import static org.assertj.core.api.Assertions.assertThat;

import org.eclipse.aether.artifact.Artifact;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArtifactFormatterTest
{

    private Artifact createArtifact( String groupId, String artifactId, String version, String extension,
                                     String classifier )
    {
        Artifact artifact = mock( Artifact.class );
        when( artifact.getArtifactId() ).thenReturn( artifactId );
        when( artifact.getGroupId() ).thenReturn( groupId );
        when( artifact.getVersion() ).thenReturn( version );
        when( artifact.getExtension() ).thenReturn( extension );
        when( artifact.getClassifier() ).thenReturn( classifier );
        return artifact;
    }

    @DataProvider( name = "getData" )
    public Object[][] dataProviderData()
    {
        return new Object[][] {
            { createArtifact( "groupId", "artifactId", "1.0-SNAPSHOT", "jar", null ),
                "groupId:artifactId:1.0-SNAPSHOT:jar" },
            { createArtifact( "groupId", "artifactId", "1.0-SNAPSHOT", "jar", "test" ),
                "groupId:artifactId:1.0-SNAPSHOT:jar:test" },
            { createArtifact( "groupId", "artifactId", "1.0-SNAPSHOT", "jar", "" ),
                "groupId:artifactId:1.0-SNAPSHOT:jar" }, };
    }

    @Test( dataProvider = "getData" )
    public void formatTest( Artifact artifact, String expectedResult )
        throws Exception
    {
        assertThat( getGAVFromArtifact( artifact ) ).isEqualTo( expectedResult );
    }

}
