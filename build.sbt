name := "LiftAjaxFileUpload"

version := "0.5"

organization := ""

scalaVersion := "2.10.0"

resolvers ++= Seq("snapshots"     at "http://oss.sonatype.org/content/repositories/snapshots",
                "releases"        at "http://oss.sonatype.org/content/repositories/releases"
                )

seq(com.github.siasia.WebPlugin.webSettings :_*)

unmanagedResourceDirectories in Test <+= (baseDirectory) { _ / "src/main/webapp" }

scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature", "-language:implicitConversions")

libraryDependencies ++= {
  val liftVersion = "2.5-RC2"
  Seq(
    "net.liftweb"             % "lift-webkit_2.10"        % liftVersion           % "compile",
    "org.eclipse.jetty"       % "jetty-webapp"            % "8.1.9.v20130131"     % "container,test",
    "org.eclipse.jetty.orbit" % "javax.servlet"           % "3.0.0.v201112011016" % "container,test" artifacts Artifact("javax.servlet", "jar", "jar"),
    "ch.qos.logback"          % "logback-classic"         % "1.0.9",
    "org.specs2"              % "specs2_2.10"             % "1.14"              % "test"
  )
}

// If using JRebel
scanDirectories in Compile := Nil
