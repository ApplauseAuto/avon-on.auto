matrixJob("RegressionTestMatrix") {
    logRotator {
        daysToKeep(5)
        numToKeep(5)
    }

    axes {
        text('device', 'Galaxy.S5_6.0.1', 'Galaxy.S7_7.0', 'Galaxy.S8_8.0', 'Google.Pixel_8.1', 'iPhone.5S_10.3.3', 'iPhone.6S.Plus_10.3.2', 'iPhone.8_11.0')
        text('test',
                'BasicCheckoutFlow',
                'GuestCheckout#MastercardDebitUS',
                'GuestCheckout#MastercardProEC',
                'GuestCheckout#VisaDebitUS',
                'GuestCheckout#VisaGoldFR',
                'PaypalCheckoutFlow',
                'SearchForLocationTest',
                'LoginTest',
                'LanguageSelectionTour',
                'TimeSelectionTour',
                'LanguageSelectPeoplePrices',
                'HasChildrenTour',
                'MinParticipantsTour',
                'PickupAddressTour',
                'AdditionalInfoTour',
                'DirectDebitTest'
        )
    }

    parameters {
        choiceParam('debug', ['false', 'true'], 'Verbose logging on or off.')
        choiceParam('logToTestRail', ['true', 'false'], 'TestRail logging on or off.')
        stringParam('branch', 'master', 'The branch of the test project to build')
        stringParam('runDateStamp', '', 'PLEASE SET WHEN DOING A MANUAL BUILD')
    }

    configure { configureblock ->
        configureblock / 'executionStrategy'(class: 'org.jenkinsci.plugins.GroovyScriptMES', plugin: 'matrix-groovy-execution-strategy') {
            secureScript(plugin: 'script-security') {
                script '''
                        combinations.each{ 
                            result[it.test] = result[it.test] ?: [] 
                            result[it.test] << it 
                        } 
                        return [ result, true]'''
                sandbox 'false'
            }
            scriptFile ''
            scriptType 'script'
        }
    }

    properties {
        githubProjectUrl('https://github.com/ApplauseAuto/getyourguide.git')
    }

    publishers {
        archiveTestNG()
    }

    wrappers {

        injectPasswords {
            injectGlobalPasswords()
        }

        buildUserVars()
    }

    scm {
        git {
            remote {
                github("ApplauseAuto/getyourguide", 'ssh')
                credentials("ea02a02b-fe7b-4f81-857a-0945e354efac")
            }
            branches('origin/${branch}')
        }
    }

    steps {
        shell('rm -f env.properties')
        shell('''
				#!/bin/sh
				if [ "$device" = "Galaxy.S5_6.0.1" ]
				then
				    DRIVER_CONFIG="TO_Samsung_Galaxy_S5"
				    echo TEST_OBJECT_TOKEN=${env_android_TEST_OBJECT_TOKEN} >> env.properties
                    echo userName = "Applause GygSix" >> env.properties
                    echo userEmail = "applausegyg+6@gmail.com" >> env.properties
                    echo userPassword = "Applause123" >> env.properties
                    echo facebookName = "Julia Jones" >> env.properties
                    echo facebookEmail = "juliajonesapps@gmail.com" >> env.properties
                    echo facebookPassword = "Applause123" >> env.properties
				elif [ "$device" = "Galaxy.S7_7.0" ]
				then
				    DRIVER_CONFIG="TO_Samsung_Galaxy_S7"
				    echo TEST_OBJECT_TOKEN=${env_android_TEST_OBJECT_TOKEN} >> env.properties
                    echo userName = "Applause GygTwo" >> env.properties
                    echo userEmail = "applausegyg+2@gmail.com" >> env.properties
                    echo userPassword = "Applause123" >> env.properties
                    echo facebookName = "David Black" >> env.properties
                    echo facebookEmail = "daveblackapps@gmail.com" >> env.properties
                    echo facebookPassword = "Applause123" >> env.properties
                elif [ "$device" = "Galaxy.S8_8.0" ]
				then
				    DRIVER_CONFIG="TO_Samsung_Galaxy_S8"
				    echo TEST_OBJECT_TOKEN=${env_android_TEST_OBJECT_TOKEN} >> env.properties
                    echo userName = "Applause GygTwo" >> env.properties
                    echo userEmail = "applausegyg+2@gmail.com" >> env.properties
                    echo userPassword = "Applause123" >> env.properties
                    echo facebookName = "David Black" >> env.properties
                    echo facebookEmail = "daveblackapps@gmail.com" >> env.properties
                    echo facebookPassword = "Applause123" >> env.properties
				elif [ "$device" = "Google.Pixel_8.1" ]
				then
				    DRIVER_CONFIG="TO_Google_Pixel"
				    echo TEST_OBJECT_TOKEN=${env_android_TEST_OBJECT_TOKEN} >> env.properties
                    echo userName = "Applause GygThree" >> env.properties
                    echo userEmail = "applausegyg+3@gmail.com" >> env.properties
                    echo userPassword = "Applause123" >> env.properties
                    echo facebookName = "Michael McConnell" >> env.properties
                    echo facebookEmail = "mikemacapps@gmail.com" >> env.properties
                    echo facebookPassword = "Applause123" >> env.properties
                elif [ "$device" = "iPhone.5S_10.3.3" ]
				then
					echo "In iPhone 5s flow"
				    DRIVER_CONFIG="TO_iPhone_5s"
				    echo TEST_OBJECT_TOKEN=${env_ios_TEST_OBJECT_TOKEN} >> env.properties
                    echo userName = "Applause GygFour" >> env.properties
                    echo userEmail = "applausegyg+4@gmail.com" >> env.properties
                    echo userPassword = "Applause123" >> env.properties
                    echo facebookName = "Mary McConnell" >> env.properties
                    echo facebookEmail = "marymcapps@gmail.com" >> env.properties
                    echo facebookPassword = "Applause123" >> env.properties
				elif [ "$device" = "iPhone.6S.Plus_10.3.2" ]
				then
				    DRIVER_CONFIG="TO_iPhone_6S_Plus"
				    echo TEST_OBJECT_TOKEN=${env_ios_TEST_OBJECT_TOKEN} >> env.properties
                    echo userName = "Applause GygFour" >> env.properties
                    echo userEmail = "applausegyg+4@gmail.com" >> env.properties
                    echo userPassword = "Applause123" >> env.properties
                    echo facebookName = "Mary McConnell" >> env.properties
                    echo facebookEmail = "marymcapps@gmail.com" >> env.properties
                    echo facebookPassword = "Applause123" >> env.properties
				elif [ "$device" = "iPhone.8_11.0" ]
				then
				    DRIVER_CONFIG="TO_iPhone_8"
				    echo TEST_OBJECT_TOKEN=${env_ios_TEST_OBJECT_TOKEN} >> env.properties
                    echo userName = "Applause GygFive" >> env.properties
                    echo userEmail = "applausegyg+5@gmail.com" >> env.properties
                    echo userPassword = "Applause123" >> env.properties
                    echo facebookName = "John Smith" >> env.properties
                    echo facebookEmail = "applausegyg@gmail.com" >> env.properties
                    echo facebookPassword = "Applause123" >> env.properties
				fi	
				echo "DRIVER_CONFIG=$DRIVER_CONFIG" >> env.properties
			''')
        shell('''
	        	if [ -n "${BUILD_USER}"] 
				then
					echo "build has been started by a timer - store runDateStamp"
				    rm -f build.properties	
				    testRailDateStamp="${BUILD_TIMESTAMP}"
				    echo "testRailDateStamp=$testRailDateStamp" >> build.properties
				elif [ "${runDateStamp}" != "" ]
				then
				    echo "build started manually - grab runDateStamp"
				    rm -f build.properties	
				    testRailDateStamp="${runDateStamp}"
				    echo "testRailDateStamp=$testRailDateStamp" >> build.properties
				else
					echo "build started manually with no rundatestamp set - assume rerun"
				fi
        	''')

        environmentVariables {
            propertiesFile('env.properties')
            env('TEST_OBJECT_TOKEN', '${TEST_OBJECT_TOKEN}')
            env('TEST_OBJECT_USERNAME', '${TEST_OBJECT_USERNAME}')
        }

        environmentVariables {
            propertiesFile('build.properties')
        }

        maven {
            injectBuildVariables(true)
            goals('clean')
            goals('compile')
            goals('test')
            mavenOpts('-Xmx256m -XX:MetaspaceSize=256m')
            property('driver', 'CONFIGURED_DRIVER')
            property('suiteFile', 'run-all.xml')
            property('buildId', '81879')
            property('driverConfig', '$DRIVER_CONFIG')
            property('logATOMResults', 'false')
            property('debug', '${debug}')
            property('takeSnapShots', 'true')
            property('autoSwitchWebview', 'true')
            property('uploadApp', 'false')
            property('runId', '${test}-${device}-${BUILD_NUMBER}')
            property('testObjectAppId', 'latest')
            property('testRailUrl', 'https://appauto.testrail.net/')
            property('testRailProjectId', '18')
            property('testRailSuiteId', '76')
            property('testRailPlanName', 'Regression_${testRailDateStamp}')
            property('testRailRunName', '${device}')
            property('logResultsToTestRail', '${logToTestRail}')
            property('matrixContainerWorkspace', '/var/lib/jenkins/jobs/RegressionTestMatrix')
            property('isJenkinsRun', 'true')
            property('userName', '${userName}')
            property('userEmail', '${userEmail}')
            property('userPassword', '${userPassword}')
            property('facebookName', '${facebookName}')
            property('facebookEmail', '${facebookEmail}')
            property('facebookPassword', '${facebookPassword}')
        }
    }
}