# Powershell Core script to install Jenkins locally
$currentWorkingDir = Get-Location
$jenkinsVersion    = "2.516.2"
$jenkinsConfigs    = "$currentWorkingDir/configs"
$jcascConfigs      = "$jenkinsConfigs/casc"
$jenkinsHome       = "$currentWorkingDir/jenkins"
$jenkinsWar        = "$jenkinsHome/jenkins.war"
$jenkinsWarUrl     = "https://github.com/jenkinsci/jenkins/releases/download/jenkins-$jenkinsVersion/jenkins.war" # Long-Term Support (LTS) release


$pluginManagerVersion = "2.13.0"
$pluginManagerJar     = "$jenkinsHome/jenkins-plugin-manager-$pluginManagerVersion.jar"
$pluginManagerUrl     = "https://github.com/jenkinsci/plugin-installation-manager-tool/releases/download/$pluginManagerVersion/jenkins-plugin-manager-$pluginManagerVersion.jar"
$pluginsConfig        = "$jenkinsConfigs/plugins.yaml"
$pluginsDir           = "$jenkinsHome/plugins"

if (-NOT (Test-Path $jenkinsHome))      { New-Item -ItemType Directory -Force -Path $jenkinsHome }
if (-NOT (Test-Path $jenkinsWar))       { Invoke-WebRequest $jenkinsWarUrl -OutFile $jenkinsWar }
if (-NOT (Test-Path $pluginManagerJar)) { Invoke-WebRequest $pluginManagerUrl -OutFile $pluginManagerJar }

Write-Output "`nInstalling Jenkins Plugins..."
java -jar $pluginManagerJar --war $jenkinsWar --plugin-download-directory $pluginsDir --plugin-file $pluginsConfig

Write-Output "`nInstalling Jenkins..."
java -D"JENKINS_HOME=$jenkinsHome" -D"jenkins.install.runSetupWizard=false" -D"casc.jenkins.config=$jcascConfigs" -jar $jenkinsWar
