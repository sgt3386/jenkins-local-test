# Jenkins Test
This is used for testing Jenkins Configuration as Code (JCasC), Jenkins Pipeline as Code, Jenkins Plugin Installation Manager Tool, and Jenkins Job DSL functionality.

This repo covers different Jenkins secrets management configurations across separate branches:
* `main`: Jenkins without any secrets management software

* `secrets/hashicorp-vault`: Jenkins with Hashicorp Vault

* `secrets/1password`: Jenkins with 1Password Secrets

## Getting Started
Download and install a Java version to your local machine. I recommend installing Eclipse Temurin: https://adoptium.net/installation/. Jenkins currently requires Java 17 or Java 21.
After Java is installed to your local machine, download the repository and run the `Install-Jenkins.ps1` script. This will automatically setup a Jenkins instance locally using configuration as code.

Once setup has finished, you can access the local Jenkins instance through your web browser at http://localhost:8080/.

Mock accounts will be configured automatically and can be used to login. For these accounts, the password is the same as the username:

| User     | Role        |
| -------- | ----------- |
| admin    | FullAdmin   |
| bob      | JobAdmin    |
| bill     | JobAdmin    |
| tim      | JobManager  |
| tom      | JobManager  |
| auto     | ServiceUser |
| jane     | User        |
| john     | User        |

> **_NOTE:_** Jobs are configured to run as the "auto" user by default. Because of this, you must login to the "auto" user once for jobs to work as intended. This is because the "auto" user does not yet exist on first boot, and so jobs are running as "anonymous" which does not have any permissions. The "auto" user is created after first time login.

## Cleanup
To restart and test a new Jenkins installation, delete the `jenkins/` directory; when that directory is deleted, the `Install_Jenkins.ps1` script will re-install Jenkins from scratch.
