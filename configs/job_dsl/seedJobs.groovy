// Job DSL Groovy script that defines the job configuration for a seedJobs Jenkins multibranch pipeline
// Initial GIT_URL, BRANCH_NAME, and JENKINSFILE_PATH values are passed in through the jobs.yaml Jenkins Configuration as Code file
// Because this is configured as a Multibranch pipeline, BRANCH_NAME can be used in any other Job DSL scripts that are seeded through this job
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
                fromScm
                {
                    name(BRANCH_NAME)

                    scm
                    {
                        gitSCM
                        {
                            branches
                            {
                                branchSpec
                                {
                                    name(BRANCH_NAME)
                                }
                            }

                            browser {}

                            extensions
                            {
                                sparseCheckoutPaths
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

                            gitTool('')

                            userRemoteConfigs
                            {
                                userRemoteConfig
                                {
                                    credentialsId('')
                                    name('')
                                    refspec('')
                                    url(GIT_URL)
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
