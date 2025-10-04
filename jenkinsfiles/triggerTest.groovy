// Job DSL Groovy script that defines the job configuration for the triggerTest Jenkins pipeline
// For triggers to be present after seedJobs applies, the trigger must be present in the Job DSL script, not the pipeline Jenkinsfile
String BRANCH_NAME = "${BRANCH_NAME}" // Prevents getProperty script approval requirement
pipelineJob('triggerTest')
{
    definition
    {
        cpsScm
        {
            lightweight(true)

            scm
            {
                scriptPath("jenkinsfiles/triggerTest")

                git
                {
                    branch(BRANCH_NAME)
                    remote
                    {
                        url('https://github.com/Minreaux/jenkins-test.git')
                    }
                }
            }
        }
    }

    disabled(false)

    properties
    {
        pipelineTriggers
        {
            triggers
            {
                cron
                {
                    // Trigger this job every 5 minutes if BRANCH_NAME is main, otherwise do not trigger
                    spec(BRANCH_NAME == 'main' ? '*/5 * * * *' : '')
                }
            }
        }
    }

}
