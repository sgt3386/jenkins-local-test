// Job DSL Groovy script that defines the job configuration for the seedJobs Jenkins multibranch pipeline
// This overwrites the initial seedJobs config configured by configs/seedJobs.groovy
String JENKINSFILE_PATH = 'jenkinsfiles/seedJobs'

multibranchPipelineJob('seedJobs')
{
    description('This job is used to seed Jenkins jobs through code using Job DSL scripts')

    branchSources
    {
        branchSource
        {
            strategy
            {
                allBranchesSame
                {
                    props
                    {
                        suppressAutomaticTriggering
                        {
                            strategy('NONE')
                            triggeredBranchesRegex('^$')
                        }
                    }
                }
            }

            source
            {
                git
                {
                    remote('https://github.com/Minreaux/jenkins-test.git')

                    traits
                    {
                        // Required to filter branches
                        gitBranchDiscovery()

                        headRegexFilter
                        {
                            // A Java regular expression to restrict the names
                            // Always match main; will also match any dev prefixed branches
                            regex("main|dev.*")
                        }

                        sparseCheckoutPaths
                        {
                            extension
                            {
                                sparseCheckoutPaths
                                {
                                    sparseCheckoutPath
                                    {
                                        path(JENKINSFILE_PATH)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    factory
    {
        workflowBranchProjectFactory
        {
            scriptPath(JENKINSFILE_PATH)
        }
    }
}
