// Job DSL Groovy script that defines the job configuration for the helloWorld Jenkins multibranch pipeline
multibranchPipelineJob('helloWorld')
{
    branchSources
    {
        git
        {
            id = 'jenkins-test'
            remote('https://github.com/Minreaux/jenkins-test.git')
        }
    }

    factory
    {
        workflowBranchProjectFactory
        {
            scriptPath('jenkinsfiles/helloWorld')
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
