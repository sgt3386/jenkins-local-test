# Jenkins Configuration Code
This directory contains config files that are used to configure Jenkins entirely through code.

* The `casc/` folder holds Jenkins Configuration as Code files (JCasC) split based on the Jenkins UI categories. These are the files that Jenkins is setup to use for configuration.

* The `job_dsl/` folder holds Jenkins Job-DSL scripts that are used by JCasC on Jenkins boot in jobs.yaml.

* The `properties/` folder holds Jenkins Java properties files that are used by JCasC on Jenkins boot to load secrets for JCasC YAML files instead of through environment variables.

* The `jenkins.yaml` file is a single JCasC file that can be used for easy comparisons when downloading the JCasC file from Jenkins before adding the code to the split files.

* The `plugins.yaml` file is used with the Jenkins Plugin Installation Manager Tool.
