// Job DSL Groovy script that defines the job configuration for the jobDslBranchTest Jenkins multibranch pipeline
// This Job-DSL script is used to test the BRANCH_NAME parameter coming from the seedJobs multibranch pipeline
String BRANCH_NAME = "${BRANCH_NAME}" // Prevents getProperty script approval requirement
multibranchPipelineJob('jobDslBranchTest')
{
    branchSources
    {
        branchSource
        {
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
                                            path('jenkinsfiles/jobDslBranchTest')
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
                                    url('https://github.com/Minreaux/jenkins-test.git')
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
            scriptPath('jenkinsfiles/jobDslBranchTest')
        }
    }

    orphanedItemStrategy
    {
        defaultOrphanedItemStrategy
        {
            abortBuilds(true)
            daysToKeepStr('')
            numToKeepStr('')
            pruneDeadBranches(true)
        }
    }
}
