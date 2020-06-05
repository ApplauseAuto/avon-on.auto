// ********************************************************* GLOBAL VARIABLES ********************************************************* //
    
import groovy.io.FileType

def testClassList = []
def annotations = []
def testList = []
def categoryList = []
def testNameIndex = 0
def enabledIndex = 1
def onAndroidIndex = 2
def onIosIndex = 3
def supportedDevicesIndex = 4
def supportedMarketsIndex = 5
def categoryIndex = 6

def avonOnSuiteId = "3073"

def MARKETS = ["Argentina",
				"Bosnia_and_Herzegovina",
				"Chile",
				"Colombia",
				"Croatia",
				"Ecuador",
				"Egypt",
				"El_Salvador",
				"Estonia",
				"Finland",
				"Georgia",
				"Germany",
				"Greece",
				"Guatemala",
				"Honduras",
				"India",
				"Kazakhstan",
				"Kyrgyzstan",
				"Latvia",
				"Lithuania",
				"Mexico",
				"Morocco",
				"Nicaragua",
				"Panama",
				"Peru",
				"Philippines",
				"Portugal",
				"Russia",
				"Saudi_Arabia",
				"Serbia",
				"Slovakia",
				"South_Africa_AVON",
				"Spain",
				"Turkey",
				"Ukraine",
				"United_Kingdom",
				"Uruguay"]
                
CLUSTER_MARKETS_1 = ["Spain",
					 "United_Kingdom",
					 "Portugal",
					 "Greece",
					 "Turkey"]

CLUSTER_MARKETS_2 = ["Georgia",
					 "Kazakhstan",
					 "Kyrgyzstan",
					 "Ukraine",
					 "Russia"]
						 
CLUSTER_MARKETS_3 = ["El_Salvador",
					 "Guatemala",
					 "Honduras",
					 "Nicaragua",
					 "Panama"]

CLUSTER_MARKETS_4 = ["Egypt",
					 "Saudi_Arabia",
					 "Morocco",
					 "South_Africa_AVON"]
						 
CLUSTER_MARKETS_5 = ["Colombia",
					 "Ecuador",
					 "Peru",
					 "India",
					 "Philippines"]

CLUSTER_MARKETS_6 = ["Chile",
					 "Uruguay",
					 "Argentina",
					 "Mexico"]
						 
CLUSTER_MARKETS_7 = ["Lithuania",
					 "Estonia",
					 "Finland",
					 "Latvia",
					 "Germany"]
						 
CLUSTER_MARKETS_8 = ["Croatia",
					 "Slovakia",
					 "Serbia",
					 "Bosnia_and_Herzegovina"]
						 
def DEVICE_ANDROID_6 = [
    deviceName          : "Android_6", 
    configName          : "Dyn_Android_6"
];

def DEVICE_ANDROID_7 = [
    deviceName          : "Android_7", 
    configName          : "Dyn_Android_7"
];

def DEVICE_ANDROID_8 = [
    deviceName          : "Android_8", 
    configName          : "Dyn_Android_8"
];

def DEVICE_ANDROID_9 = [
    deviceName          : "Android_9", 
    configName          : "Dyn_Android_9"
];

def DEVICE_IPHONE_6_11 = [
    deviceName          : "iPhone_6_11", 
    configName          : "Dyn_iPhone_6_11"
];

def DEVICE_IPHONE_7_11 = [
    deviceName          : "iPhone_7_11", 
    configName          : "Dyn_iPhone_7_11"
];

def DEVICE_IPHONE_8_12 = [
    deviceName          : "iPhone_8_12", 
    configName          : "Dyn_iPhone_8_12"
];

def DEVICE_IPHONE_X_12 = [
    deviceName          : "iPhone_X_12", 
    configName          : "Dyn_iPhone_X_12"
];

def DEVICES = [
    DEVICE_ANDROID_6, DEVICE_ANDROID_7, DEVICE_ANDROID_8, DEVICE_ANDROID_9,
    DEVICE_IPHONE_6_11, DEVICE_IPHONE_7_11, DEVICE_IPHONE_8_12, DEVICE_IPHONE_X_12
]

def CLUSTER_DEVICES = ["IOS", "ANDROID"]
def ANDROID_DEVICES = [DEVICE_ANDROID_6, DEVICE_ANDROID_7, DEVICE_ANDROID_8, DEVICE_ANDROID_9]
def IOS_DEVICES = [DEVICE_IPHONE_6_11, DEVICE_IPHONE_7_11, DEVICE_IPHONE_8_12, DEVICE_IPHONE_X_12]

def androidDynamicDeviceScript = '''#!/bin/bash
                    array[0]="Dyn_Android_6"
                    array[1]="Dyn_Android_7"
                    array[2]="Dyn_Android_8"
                    array[3]="Dyn_Android_9"
                    
                    size=${#array[@]}
                    index=$(($RANDOM % $size))
                    echo ${array[$index]}
                    
                    echo "DRIVER_CONFIG=${array[$index]}" >> env.properties
                    echo "TEST_OBJECT_TOKEN=$TEST_OBJECT_TOKEN_ANDROID" >> env.properties;''';
            
def iosDynamicDeviceScript = '''#!/bin/bash
        array[0]="Dyn_iPhone_6_11"
        array[1]="Dyn_iPhone_7_11"
        array[2]="Dyn_iPhone_8_12"
        array[3]="Dyn_iPhone_X_12"
        
        size=${#array[@]}
        index=$(($RANDOM % $size))
        echo ${array[$index]}
        
        echo "DRIVER_CONFIG=${array[$index]}" >> env.properties
        echo "TEST_OBJECT_TOKEN=$TEST_OBJECT_TOKEN_IOS" >> env.properties;''';
        
def dynamicDeviceScript = '''#!/bin/bash
	if [[ $device == "ANDROID" ]]; then
	    array[0]="Dyn_Android_6"
        array[1]="Dyn_Android_7"
        array[2]="Dyn_Android_8"
        array[3]="Dyn_Android_9"
        
        size=${#array[@]}
        index=$(($RANDOM % $size))
        echo ${array[$index]}
        
        echo "DRIVER_CONFIG=${array[$index]}" >> env.properties
        echo "TEST_OBJECT_TOKEN=$TEST_OBJECT_TOKEN_ANDROID" >> env.properties;
	 else
        array[0]="Dyn_iPhone_6_11"
        array[1]="Dyn_iPhone_7_11"
        array[2]="Dyn_iPhone_8_12"
        array[3]="Dyn_iPhone_X_12"
        
        size=${#array[@]}
        index=$(($RANDOM % $size))
        echo ${array[$index]}
        
        echo "DRIVER_CONFIG=${array[$index]}" >> env.properties
        echo "TEST_OBJECT_TOKEN=$TEST_OBJECT_TOKEN_IOS" >> env.properties;
     fi'''; 

def AVON_ON_GITHUB = [
    url : "ApplauseAuto/avon.auto",
    deployKey : "ea02a02b-fe7b-4f81-857a-0945e354efac"
];

String[] SETUP_JOBS = ["run_dsl"]
String[] DEVELOPMENT_JOBS = ["Dev.Avon.On.Single.Test", "Avon.On.Android.Dev.Matrix", "Avon.On.iOS.Dev.Matrix", "Avon.On.Android.Dev.Single.Test.All.Markets", "Avon.On.iOS.Dev.Single.Test.All.Markets"]
String[] AVON_ON_REGRESSION_JOBS = ["Avon.On.Markets.Android", "Avon.On.Markets.iOS", "Avon.On.Regression"]
String[] AVON_ON_CLUSTER_JOBS = ["Avon.On.Run.1", "Avon.On.Run.2", "Avon.On.Run.3", "Avon.On.Run.4", "Avon.On.Run.5"]

NAGINATOR_WAIT_TIME = 60
NAGINATOR_NUMBER_OF_RETRIES = 1

String testDirPath = "${WORKSPACE}/src/test/java/com/applause/auto/test";

def testsDirectory = new File(testDirPath)
testsDirectory.eachFileRecurse (FileType.FILES) { file ->
  if (!file.path.contains("helper") && !file.path.contains("BaseTest") && !file.path.contains("NavigateTo")) {
     testClassList << file
  }
}

testClassList.each {
     def testName = it.name.replace(".java", "");
     
     // Getting "@Test(...)" class annotation 
     def annotation = it.text.trim().replaceAll("[\n\r]", "");
     annotation = (annotation =~ /@Test\(.*?groups = (.*?)\)/)[0][0]
     
     // Setting default test entry values:
     // testName, enabled, onAndroid, onIos, supportedDevices, supportedMarkets, category
     def testEntry = [testName, true, true, true, null, MARKETS, "empty"]
     
     // Setting entry values based on groups value in @Test annotation
     if (annotation.contains("DISABLED") || annotation.contains("enabled = false") || annotation.contains("enabled=false")) {
         testEntry[enabledIndex] = false;
     }
     
     // setting default supported drivers 
     def supportedDevices = [:]
    
     DEVICES.each {
         supportedDevices[it.deviceName] = false;
     }
     
     if (annotation.contains("ALL_PLATFORMS")) {
         DEVICES.each {
             supportedDevices[it.deviceName] = true;
         }
     } else if (annotation.contains("ONLY_ANDROID")) {
         ANDROID_DEVICES.each {
             supportedDevices[it.deviceName] = true;
         }
         
         testEntry[onIosIndex] = false;
     } else if (annotation.contains("ONLY_IOS")) {
         IOS_DEVICES.each {
             supportedDevices[it.deviceName] = true;
         }
         
         testEntry[onAndroidIndex] = false;
     }
      
     testEntry[supportedDevicesIndex] = supportedDevices;
     
     def categoryRegex = /(?<=CATEGORY_)(.*?)(?=\,)/
     def foundCategory = (annotation =~ categoryRegex)
     testEntry[categoryIndex] = foundCategory[0][0];
     categoryList.add(foundCategory[0][0]);
     
     if (annotation.contains("MARKET")) {
        def marketRegex = /(?<=MARKET_)(.*?)(?=,|}| )/
        def foundMarkets = (annotation =~ marketRegex)
        def numberOfMarkets = foundMarkets.count
        def testEntryWithMarket = new ArrayList<>(testEntry);
        def supportedMarkets = [];

        (0..numberOfMarkets-1).each {
            def marketValue = foundMarkets[it][0];
            
            MARKETS.each {
                if (marketValue.toLowerCase().equals(it.toLowerCase())) {
                    supportedMarkets.add(it);
                }
            }    
        }
        
        testEntryWithMarket[supportedMarketsIndex] = supportedMarkets;
        testList.add(testEntryWithMarket);
     } else {
         testList.add(testEntry);
     }
}

categoryList = categoryList.toSet().sort()
def categoryCombinationConditions = [:]

categoryList.each {
    def catName = it
    def condition = "(";
    
    testList.each {
        if (it[categoryIndex].equals(catName)) {
            if (condition.startsWith("(test")) {
                condition += " || ";
            }
            
            condition += "test=="
            condition += "'" + it[testNameIndex] + "'";
        }
    }
    
    condition += ")";
    categoryCombinationConditions.put(catName, condition);
}

testList.sort{ x, y -> 
    x[testNameIndex] <=> y[testNameIndex]
}

testList.each {
    println it
}

println "\nTotal number of tests: " + testList.size() + "\n"
println "Disabled test cases:\n"
    
testList.each {
    if (!it[enabledIndex]) {
        println it;
    }
}

println "\n"

testListCopy = testList;
enabledIndexCopy = enabledIndex;

def int getCount(int envIndex, int platformIndex) {
    int count = 0;
    
    testListCopy.each {
        if (it[enabledIndexCopy] && it[envIndex] && it[platformIndex]) {
            count++;
        }
    }
    
    return count;
}

def excludeDevices = { Boolean needsOrOperator, String conditionName, String deviceName, Map supportedDevices ->
    if (supportedDevices.get(deviceName) == false) {
        if(needsOrOperator) {
            conditionName += "||"
        }
        
        conditionName += "device=='" + deviceName + "'"
        needsOrOperator = true
    }
    
    return [conditionName, needsOrOperator]
}

def processConditionForSupportedDevices = { String conditionName ->
    testList.each { testName, enabled, onAndroid, onIos, supportedDevices, supportedMarkets, category ->
        if (supportedDevices.any{ key, value -> value == false }){
            // adding the test
            if(conditionName.contains("test") == false){
                conditionName += "!((test=='"+ testName + "'&&("
            } else {
                conditionName += " || (test=='"+ testName + "'&&("
            }
            
            // exclude the unsupported devices       
            needsOrOperator = false; 
            
            DEVICES.each {
                (conditionName, needsOrOperator) = excludeDevices(needsOrOperator, conditionName, it.deviceName, supportedDevices)
            }
            
            // end the current condition part for this suite
            conditionName += "))"
        }
        
        // end the condition
        if (testList.last()[testNameIndex].equals(testName)) {
            if (conditionName.size() != 0) {
                conditionName += ")"
            }
        }
    }
    
    return conditionName
}

def excludeMarkets = { Boolean needsOrOperator, String conditionName, String marketName, List supportedMarkets ->
    if (!supportedMarkets.contains(marketName)) {
        if(needsOrOperator) {
            conditionName += "||"
        }
        
        conditionName += "market=='" + marketName + "'"
        needsOrOperator = true
    }
    
    return [conditionName, needsOrOperator]
}

def processConditionForSupportedMarkets = { String conditionName ->
    testList.each { testName, enabled, onAndroid, onIos, supportedDevices, supportedMarkets, category ->
        if (supportedMarkets.size() != MARKETS.size()) {
            // adding the test
            if(!conditionName.contains("test")){
                conditionName += "!((test=='"+ testName + "'&&("
            } else {
                conditionName += " || (test=='"+ testName + "'&&("
            }
            
            // exclude the unsupported markets       
            needsOrOperator = false; 
            
            MARKETS.each {
                (conditionName, needsOrOperator) = excludeMarkets(needsOrOperator, conditionName, it, supportedMarkets)
            }
            
            // end the current condition part for this suite
            conditionName += "))"
        }
        
        // end the condition
        if (testList.last()[testNameIndex].equals(testName)) {
            if (conditionName.size() != 0) {
                conditionName += ")"
            }
        }
    }
    
    return conditionName
}

def processConditionForSupportedClusterMarkets = { String conditionName ->
    testList.each { testName, enabled, onAndroid, onIos, supportedDevices, supportedMarkets, category ->
        if (supportedMarkets.size() != MARKETS.size()) {
            // adding the test
            if(!conditionName.contains("test")){
                conditionName += "!((test=='"+ testName + "'&&("
            } else {
                conditionName += " || (test=='"+ testName + "'&&("
            }
            
            // exclude the unsupported markets       
            needsOrOperator = false; 
            
            MARKETS.each {
                (conditionName, needsOrOperator) = excludeMarkets(needsOrOperator, conditionName, it, supportedMarkets)
            }
            
            // end the current condition part for this suite
            conditionName += "))"
        }
            
        // exclude the unsupported devices
        if (!onAndroid) {
            needsOrOperator = false
                        
            if(!conditionName.contains("test")){
                conditionName += "!((test=='"+ testName + "'&&("
            } else {
                conditionName += " || (test=='"+ testName + "'&&("
            }
            
            if(needsOrOperator) {
                conditionName += "||"
            }
            
            conditionName += "device=='ANDROID'"
            needsOrOperator = true
        
            // end the current condition part for this suite
            conditionName += "))"
        } 
        
        if (!onIos) {
            needsOrOperator = false
            
            if(!conditionName.contains("test")){
                conditionName += "!((test=='"+ testName + "'&&("
            } else {
                conditionName += " || (test=='"+ testName + "'&&("
            }
            
            if(needsOrOperator) {
                conditionName += "||"
            }
            
            conditionName += "device=='IOS'"
            needsOrOperator = true
        
            // end the current condition part for this suite
            conditionName += "))"
        }
        
        // end the condition
        if (testList.last()[testNameIndex].equals(testName)) {
            if (conditionName.size() != 0) {
                conditionName += ")"
            }
        }
    }
    
    return conditionName
}

def avonOnFilterTestCases = { Map testCases ->
    testList.each { testName, enabled, onAndroid, onIos, supportedDevices, supportedMarkets, category ->
        if (enabled) {
            testCases.put(testName, category)
        }
    }
    
    return testCases
}
    

def avonOnFilterAndroidTestCases = { Map testCases ->
    testList.each { testName, enabled, onAndroid, onIos, supportedDevices, supportedMarkets, category ->
        if (enabled && onAndroid) {
            testCases.put(testName, category)
        }
    }
    
    return testCases
}

def avonOnFilterIosTestCases = { Map testCases ->
    testList.each { testName, enabled, onAndroid, onIos, supportedDevices, supportedMarkets, category ->
        if (enabled && onIos) {
            testCases.put(testName, category)
        }
    }
    
    return testCases
}

public List getMarkets(int clusterIndex) {
	def markets = [];
	
	if (CLUSTER_MARKETS_1[clusterIndex] != null) {
		markets.add(CLUSTER_MARKETS_1[clusterIndex]);
	}
	
	if (CLUSTER_MARKETS_2[clusterIndex] != null) {
		markets.add(CLUSTER_MARKETS_2[clusterIndex]);
	}
	
	if (CLUSTER_MARKETS_3[clusterIndex] != null) {
		markets.add(CLUSTER_MARKETS_3[clusterIndex]);
	}
	
	if (CLUSTER_MARKETS_4[clusterIndex] != null) {
		markets.add(CLUSTER_MARKETS_4[clusterIndex]);
	}
	
	if (CLUSTER_MARKETS_5[clusterIndex] != null) {
		markets.add(CLUSTER_MARKETS_5[clusterIndex]);
	}
	
	if (CLUSTER_MARKETS_6[clusterIndex] != null) {
		markets.add(CLUSTER_MARKETS_6[clusterIndex]);
	}
	
	if (CLUSTER_MARKETS_7[clusterIndex] != null) {
		markets.add(CLUSTER_MARKETS_7[clusterIndex]);
	}
	
	if (CLUSTER_MARKETS_8[clusterIndex] != null) {
		markets.add(CLUSTER_MARKETS_8[clusterIndex]);
	}
	
	return markets;
}
    
// call the above Closure to create the correct conditions for each environment
String marketCombinationsConditions = processConditionForSupportedMarkets("")
String clusterMarketCombinationsConditions = processConditionForSupportedClusterMarkets("")
String deviceCombinationsConditions = processConditionForSupportedDevices("")

// call the above Closures to filter test cases according to the environment you are using
def avonOnTests = avonOnFilterTestCases([:])
def avonOnAndroidTests = avonOnFilterAndroidTestCases([:])
def avonOnIosTests = avonOnFilterIosTestCases([:])

// jobName, suiteId, markets, githubDetails, branchName, hasTrigger, triggerTime, useLogToTestRail, combinationsConditions, testCases, categoryFilter, deviceScript, retryFailedRuns, emailAfterRun, useTunnel
marketMatrices = [
    // AvonOn
    [AVON_ON_REGRESSION_JOBS[0], avonOnSuiteId, MARKETS, AVON_ON_GITHUB, "master", false, "", true, marketCombinationsConditions, avonOnAndroidTests, "", androidDynamicDeviceScript, true, false, true],
    [AVON_ON_REGRESSION_JOBS[1], avonOnSuiteId, MARKETS, AVON_ON_GITHUB, "master", false, "", true, marketCombinationsConditions, avonOnIosTests, "", iosDynamicDeviceScript, true, false, true]
];

// jobName, suiteId, githubDetails, branchName, hasTrigger, triggerTime, useLogToTestRail, combinationsConditions, testCases, categoryFilter, retryFailedRuns, emailAfterRun, useTunnel, devices
regressionMatrices = [
    // AvonOn
    [AVON_ON_REGRESSION_JOBS[2], avonOnSuiteId, AVON_ON_GITHUB, "master", false, "", true, deviceCombinationsConditions, avonOnTests, "", true, false, true, DEVICES],

    // Dev AvonOn
    [DEVELOPMENT_JOBS[1], avonOnSuiteId, AVON_ON_GITHUB, "master", true, "", false, deviceCombinationsConditions, avonOnAndroidTests, "", false, false, true, ANDROID_DEVICES],
    [DEVELOPMENT_JOBS[2], avonOnSuiteId, AVON_ON_GITHUB, "master", true, "", false, deviceCombinationsConditions, avonOnIosTests, "", false, false, true, IOS_DEVICES]
];

singleTestAllMarketsMatrices = [
	// jobName, suiteId, markets, githubDetails, branchName, hasTrigger, triggerTime, useLogToTestRail, combinationsConditions, testCases, categoryFilter, deviceScript, retryFailedRuns, emailAfterRun, useTunnel
	[DEVELOPMENT_JOBS[3], avonOnSuiteId, MARKETS, AVON_ON_GITHUB, "master", false, "", true, "", "", "", androidDynamicDeviceScript, false, false, true],
    [DEVELOPMENT_JOBS[4], avonOnSuiteId, MARKETS, AVON_ON_GITHUB, "master", false, "", true, "", "", "", iosDynamicDeviceScript, false, false, true]
];


// jobName, suiteId, markets, githubDetails, branchName, hasTrigger, triggerTime, useLogToTestRail, combinationsConditions, testCases, categoryFilter, deviceScript, retryFailedRuns, emailAfterRun, useTunnel
clusterMatrices = [
    // AvonOn
    [AVON_ON_CLUSTER_JOBS[0], avonOnSuiteId, getMarkets(0), AVON_ON_GITHUB, "master", true, "0 2 * * 1,3,5", true, clusterMarketCombinationsConditions, avonOnTests, "", dynamicDeviceScript, true, false, true],
    [AVON_ON_CLUSTER_JOBS[1], avonOnSuiteId, getMarkets(1), AVON_ON_GITHUB, "master", true, "0 5 * * 1,3,5", true, clusterMarketCombinationsConditions, avonOnTests, "", dynamicDeviceScript, true, false, true],
    [AVON_ON_CLUSTER_JOBS[2], avonOnSuiteId, getMarkets(2), AVON_ON_GITHUB, "master", true, "0 8 * * 1,3,5", true, clusterMarketCombinationsConditions, avonOnTests, "", dynamicDeviceScript, true, false, true],
    [AVON_ON_CLUSTER_JOBS[3], avonOnSuiteId, getMarkets(3), AVON_ON_GITHUB, "master", true, "0 11 * * 1,3,5", true, clusterMarketCombinationsConditions, avonOnTests, "", dynamicDeviceScript, true, false, true],
    [AVON_ON_CLUSTER_JOBS[4], avonOnSuiteId, getMarkets(4), AVON_ON_GITHUB, "master", true, "0 14 * * 1,3,5", true, clusterMarketCombinationsConditions, avonOnTests, "", dynamicDeviceScript, true, false, true]
];

listViews = [
	['0.ON.Cluster', 'Test suite cluster triggers for Avon On.', AVON_ON_CLUSTER_JOBS],
	['1.ON.Regression', 'Test suite triggers for Avon On.', AVON_ON_REGRESSION_JOBS],
	['2.ON.Dev', "Development jobs.", DEVELOPMENT_JOBS]
];

// ********************************************************* JOBS ********************************************************* //

println "\nBuilding Market matrices"
println "------------------------"

marketMatrices.each { jobName, suiteId, markets, githubDetails, branchName, hasTrigger, triggerTime, useLogToTestRail, combinationsCondition, testCases, categoryFilter, deviceScript, retryFailedRuns, emailAfterRun, useTunnel ->
	matrixJob(jobName) {
		combinationFilter(combinationsCondition)
		axes {
	        text('market', markets as String[])
	        text('test', testCases.keySet() as String[])
	    }
	    parameters {
	    	booleanParam('logResultsToTestRail', true, 'Whether or not to log results to TestRail.')
			stringParam('runDateStamp', '', 'PLEASE SET WHEN DOING A MANUAL BUILD')
			stringParam('branch', branchName, 'The branch of the test project to build')
			booleanParam('debug', false, 'Verbose logging on or off.  This is overridden by the trigger.')
			matrixCombinationsParameterDefinition {
				name('COMBINATIONS')
				description('Choose which combinations to run')
				defaultCombinationFilter('') 
				shortcutList {					
					resultShortcut {
						name('Successful')
						exact(false)
						resultsToCheck(["SUCCESS"])
					}
					
					resultShortcut {
						name('Failed')
						exact(false)
						resultsToCheck(["FAILURE"])
					}
					
					// shortcut links for markets
					/*markets.each {
						def market = it
                        println "market: " + market
						combinationFilterShortcut {
							name(market)
							combinationFilter('market=="' + market + '"')
						}
					}*/
					
					// shortcut links for categories
					/*categoryList.each {
						def category = it
						def filter = categoryCombinationConditions.get(category)
						combinationFilterShortcut {
							name(category)
							combinationFilter(filter)
						}
					}*/
					
					none()
				}
			}
	    }
	    wrappers {
			injectPasswords {
				injectGlobalPasswords()
		  	}
		  	buildUserVars()
			timeout{
		  		noActivity(600)
		  		failBuild()
		  	}
		}
	    scm {
			git {
	  			remote {
	                github(githubDetails.url, 'ssh')
					credentials(githubDetails.deployKey)
        		}
				branches('*/${branch}')
		  	}
		}
	    steps {	        
    		shell('rm -f env.properties')
    		shell(deviceScript)
			shell('''
	        	if [ -n "${NAGINATOR_BUILD_NUMBER}" ] &&  [ -z "${BUILD_USER}" ] 
				then
						echo "\n\n########## (CASE #1) Build started by Naginator after a timer run. Grab the old runDateStamp value to log to the correct test plan on TestRail."
				elif [ -z "${BUILD_USER}" ] 
				then
					echo "\n\n########## (CASE #2) Build has been started by a timer. Store current BUILD_TIMESTAMP to use it for later re-run/matrix-reloaded/Naginator runs."
				    rm -f /var/lib/jenkins/jobs/''' + jobName + '''/build.properties	
				    testRailDateStamp="${BUILD_TIMESTAMP}"
				    echo "testRailDateStamp=$testRailDateStamp" >> /var/lib/jenkins/jobs/''' + jobName + '''/build.properties
				elif [ "${runDateStamp}" != "" ]
				then
				    echo "\n\n########## (CASE #3) Build either started manually by a user or is a Naginator re-run after a manual run (with runDateStamp value). Grab the provided runDateStamp value to log to the correct test plan on TestRail."
				    rm -f /var/lib/jenkins/jobs/''' + jobName + '''/build.properties	
				    testRailDateStamp="${runDateStamp}"
				    echo "testRailDateStamp=$testRailDateStamp" >> /var/lib/jenkins/jobs/''' + jobName + '''/build.properties
				else
					echo "\n\n########## (CASE #4) Build either started manually by a user or is a Naginator re-run after a manual run (without runDateStamp value). Grab the old runDateStamp value to log to the correct test plan on TestRail."
			fi''')
	        environmentVariables {
				propertiesFile('env.properties')
	        }
	        environmentVariables {
	            propertiesFile('/var/lib/jenkins/jobs/' + jobName + '/build.properties')
	        }
    		maven {
				goals('clean')
				goals('compile')
				goals('test')
				injectBuildVariables(false)
				
				property('test','${test}')
				property('market','${market}')
				property('suiteFile','run.xml')
				property('driver','CONFIGURED_DRIVER')
				property('driverConfig','${DRIVER_CONFIG}')
				property('buildId', '81879')
				property('debug','${debug}')
				property('takeScreenshots','true')
				property('runDateStamp', '${BUILD_TIMESTAMP}')
				property('ciJobName', '${BUILD_NUMBER}-${test}-${DRIVER_CONFIG}')
				property('isJenkinsRun', 'true')
				property('skipMysqlLogs', 'true')
				property('runId', '${test}-${market}-${BUILD_TIMESTAMP}')
				
				if(useLogToTestRail){
					property('logResultsToTestRail', 'true')
					property('testRailUserName', '${TESTRAIL_USERNAME}')
					property('testRailPassword', '${TESTRAIL_TOKEN}')
					property('testRailUrl', 'https://appauto.testrail.net')
					property('testRailProjectId', '40')
					property('testRailSuiteId', suiteId)
					
					if (jobName.contains("Android")) {
						property('testRailPlanName', 'Avon ON - ANDROID - ${testRailDateStamp}')
					} else {
						property('testRailPlanName', 'Avon ON - IOS - ${testRailDateStamp}')
					}
					
					property('testRailRunName', '${market}')
				}
				
				property('matrixContainerWorkspace', '/var/lib/jenkins/jobs/' + jobName)
				property('testObjectAppId', 'latest')
				mavenOpts('-Xmx256m -XX:MetaspaceSize=256m')
			}
	    }
	    if(hasTrigger){
		    triggers{
		    	authenticationToken('f8ofjow9epspfo')
				cron(triggerTime)
			}
		}
	    publishers {
    		archiveTestNG("**/testng-results.xml") {
	            showFailedBuildsInTrendGraph()
	            markBuildAsFailureOnFailedConfiguration()
    		}
			
			if (emailAfterRun) {
				mailer('hmcconnell@applause.com nfolayan@applausemail.com', false, true)
			}
			
    		wsCleanup()
    		if(retryFailedRuns){
        		naginatorPublisher {
        			rerunIfUnstable(true)
        			rerunMatrixPart(true)
        			maxSchedule(NAGINATOR_NUMBER_OF_RETRIES)
        			regexpForRerun("")
        			checkRegexp(false)
        			noChildStrategy("DontRun")
        			delay {
        				fixedDelay {
							delay(NAGINATOR_WAIT_TIME)
						}
        			}
        		}
			}
		}
		logRotator {
			numToKeep(100)
		}
	}
}

println "\nBuilding regression matrices"
println "----------------------------"

regressionMatrices.each { jobName, suiteId, githubDetails, branchName, hasTrigger, triggerTime, useLogToTestRail, combinationsCondition, testCases, categoryFilter, retryFailedRuns, emailAfterRun, useTunnel, devices ->
	matrixJob(jobName) {
		combinationFilter(combinationsCondition)
		axes {
	        text('device', devices.deviceName as String[])
	        
	        if (categoryFilter.isEmpty()) {
	        	text('test', testCases.keySet() as String[])
	        } else {
	        	text('test', testCases.findAll { it.value == categoryFilter }.keySet() as String[])
	        }
	    }
	    parameters {
			stringParam('runDateStamp', '', 'PLEASE SET WHEN DOING A MANUAL BUILD')
			stringParam('branch', branchName, 'The branch of the test project to build')
			stringParam('market', 'United_Kingdom', 'Market to test against.')
			matrixCombinationsParameterDefinition {
				name('COMBINATIONS')
				description('Choose which combinations to run')
				defaultCombinationFilter('') 
				shortcutList {					
					resultShortcut {
						name('Successful')
						exact(false)
						resultsToCheck(["SUCCESS"])
					}
					
					resultShortcut {
						name('Failed')
						exact(false)
						resultsToCheck(["FAILURE"])
					}
					
					// shortcut links for devices
					/*devices.each {
						def device = it.deviceName
						println "reg device: " + device
						combinationFilterShortcut {
							name(device)
							combinationFilter('device=="' + device + '"')
						}
					}*/
					
					// shortcut links for categories
					/*categoryList.each {
						def category = it
						def filter = categoryCombinationConditions.get(category)
						combinationFilterShortcut {
							name(category)
							combinationFilter(filter)
						}
					}*/
					
					none()
				}
			}
	    }
	    wrappers {
			injectPasswords {
				injectGlobalPasswords()
		  	}
		  	buildUserVars()
			timeout{
		  		noActivity(600)
		  		failBuild()
		  	}
		}
	    scm {
			git {
	  			remote {
	                github(githubDetails.url, 'ssh')
					credentials(githubDetails.deployKey)
        		}
				branches('*/${branch}')
		  	}
		}
	    steps {	        
    		environmentVariables {
    			devices.each {
	            	env(it.deviceName, it.configName)
	            }
	        }
	        
    		shell('rm -f env.properties')
	        shell('''#!/bin/bash
                DRIVER_CONFIG=${!device}
                
				echo "DRIVER_CONFIG=$DRIVER_CONFIG" >> env.properties
                if [[ $DRIVER_CONFIG == *"Android"* ]]; then
	              echo "TEST_OBJECT_TOKEN=$TEST_OBJECT_TOKEN_ANDROID" >> env.properties
	            else
	              echo "TEST_OBJECT_TOKEN=$TEST_OBJECT_TOKEN_IOS" >> env.properties
	            fi''')
			shell('''
	        	if [ -n "${NAGINATOR_BUILD_NUMBER}" ] &&  [ -z "${BUILD_USER}" ] 
				then
						echo "\n\n########## (CASE #1) Build started by Naginator after a timer run. Grab the old runDateStamp value to log to the correct test plan on TestRail."
				elif [ -z "${BUILD_USER}" ] 
				then
					echo "\n\n########## (CASE #2) Build has been started by a timer. Store current BUILD_TIMESTAMP to use it for later re-run/matrix-reloaded/Naginator runs."
				    rm -f /var/lib/jenkins/jobs/''' + jobName + '''/build.properties	
				    testRailDateStamp="${BUILD_TIMESTAMP}"
				    echo "testRailDateStamp=$testRailDateStamp" >> /var/lib/jenkins/jobs/''' + jobName + '''/build.properties
				elif [ "${runDateStamp}" != "" ]
				then
				    echo "\n\n########## (CASE #3) Build either started manually by a user or is a Naginator re-run after a manual run (with runDateStamp value). Grab the provided runDateStamp value to log to the correct test plan on TestRail."
				    rm -f /var/lib/jenkins/jobs/''' + jobName + '''/build.properties	
				    testRailDateStamp="${runDateStamp}"
				    echo "testRailDateStamp=$testRailDateStamp" >> /var/lib/jenkins/jobs/''' + jobName + '''/build.properties
				else
					echo "\n\n########## (CASE #4) Build either started manually by a user or is a Naginator re-run after a manual run (without runDateStamp value). Grab the old runDateStamp value to log to the correct test plan on TestRail."
			fi''')
	        environmentVariables {
	            propertiesFile('/var/lib/jenkins/jobs/' + jobName + '/build.properties')
	        }
	        environmentVariables {
	            propertiesFile('env.properties')
	        }
    		maven {
				goals('clean')
				goals('compile')
				goals('test')
				injectBuildVariables(false)
				
				property('test','$test')
				property('market','${market}')
				property('suiteFile','run.xml')
				property('driver','CONFIGURED_DRIVER')
				property('driverConfig','${DRIVER_CONFIG}')
				property('buildId', '81879')
				property('debug','false')
				property('takeScreenshots','${takeScreenshots}')
				property('runDateStamp', '${BUILD_TIMESTAMP}')
				property('ciJobName', '${test}-${device}-${BUILD_NUMBER}')
				property('isJenkinsRun', 'true')
				property('skipMysqlLogs', 'true')
				property('runId', '${test}-${device}-${BUILD_TIMESTAMP}')
				
				if(useLogToTestRail){
					property('logResultsToTestRail', 'true')
					property('testRailUserName', '${TESTRAIL_USERNAME}')
					property('testRailPassword', '${TESTRAIL_TOKEN}')
					property('testRailUrl', 'https://appauto.testrail.net')
					property('testRailProjectId', '40')
					property('testRailSuiteId', suiteId)
					property('testRailPlanName', 'Avon ON - ${market} - Regression - ${testRailDateStamp}')					
					property('testRailRunName', '${device}')
				}
				
				property('matrixContainerWorkspace', '/var/lib/jenkins/jobs/' + jobName)
				property('testObjectAppId', 'latest')
				mavenOpts('-Xmx256m -XX:MetaspaceSize=256m')
			}
	    }
	    if(hasTrigger){
		    triggers{
		    	authenticationToken('f8ofjow9epspfo')
				cron(triggerTime)
			}
		}
	    publishers {
    		archiveTestNG("**/testng-results.xml") {
	            showFailedBuildsInTrendGraph()
	            markBuildAsFailureOnFailedConfiguration()
    		}
			
			if (emailAfterRun) {
				mailer('hmcconnell@applause.com nfolayan@applausemail.com', false, true)
			}
			
    		wsCleanup()
    		if(retryFailedRuns){
        		naginatorPublisher {
        			rerunIfUnstable(true)
        			rerunMatrixPart(true)
        			maxSchedule(NAGINATOR_NUMBER_OF_RETRIES)
        			regexpForRerun("")
        			checkRegexp(false)
        			noChildStrategy("DontRun")
        			delay {
        				fixedDelay {
							delay(NAGINATOR_WAIT_TIME)
						}
        			}
        		}
			}
		}
		logRotator {
			numToKeep(100)
		}
	}
}


println "\nBuilding dev job"
println "----------------"

singleTestAllMarketsMatrices.each { jobName, suiteId, markets, githubDetails, branchName, hasTrigger, triggerTime, useLogToTestRail, combinationsCondition, testCases, categoryFilter, deviceScript, retryFailedRuns, emailAfterRun, useTunnel ->
	matrixJob(jobName) {
		axes {
	        text('market', markets as String[])
	    }
	    parameters {
			stringParam('test', 'LoginTest', 'Test class name.')
			stringParam('branch', branchName, 'The branch of the test project to build')
			matrixCombinationsParameterDefinition {
				name('COMBINATIONS')
				description('Choose which combinations to run')
				defaultCombinationFilter('') 
				shortcutList {					
					resultShortcut {
						name('Successful')
						exact(false)
						resultsToCheck(["SUCCESS"])
					}
					
					resultShortcut {
						name('Failed')
						exact(false)
						resultsToCheck(["FAILURE"])
					}
					
					none()
				}
			}
	    }
	    wrappers {
			injectPasswords {
				injectGlobalPasswords()
		  	}
		  	buildUserVars()
			timeout{
		  		noActivity(600)
		  		failBuild()
		  	}
		}
	    scm {
			git {
	  			remote {
	                github(githubDetails.url, 'ssh')
					credentials(githubDetails.deployKey)
        		}
				branches('*/${branch}')
		  	}
		}
	    steps {	        
    		shell('rm -f env.properties')
    		shell(deviceScript)
	        environmentVariables {
				propertiesFile('env.properties')
	        }
    		maven {
				goals('clean')
				goals('compile')
				goals('test')
				injectBuildVariables(false)
				
				property('test','${test}')
				property('market','${market}')
				property('suiteFile','run.xml')
				property('driver','CONFIGURED_DRIVER')
				property('driverConfig','${DRIVER_CONFIG}')
				property('buildId', '81879')
				property('debug','false')
				property('takeScreenshots','true')
				property('runDateStamp', 'test')
				property('ciJobName', '${BUILD_NUMBER}-${test}-${DRIVER_CONFIG}')
				property('isJenkinsRun', 'true')
				property('skipMysqlLogs', 'true')
				property('runId', '${test}-${market}-${BUILD_TIMESTAMP}')				
				property('matrixContainerWorkspace', '/var/lib/jenkins/jobs/' + jobName)
				property('testObjectAppId', 'latest')
				mavenOpts('-Xmx256m -XX:MetaspaceSize=256m')
			}
	    }
	    if(hasTrigger){
		    triggers{
		    	authenticationToken('f8ofjow9epspfo')
				cron(triggerTime)
			}
		}
	    publishers {
    		archiveTestNG("**/testng-results.xml") {
	            showFailedBuildsInTrendGraph()
	            markBuildAsFailureOnFailedConfiguration()
    		}
			
			if (emailAfterRun) {
				mailer('hmcconnell@applause.com nfolayan@applausemail.com', false, true)
			}
			
    		wsCleanup()
    		if(retryFailedRuns){
        		naginatorPublisher {
        			rerunIfUnstable(true)
        			rerunMatrixPart(true)
        			maxSchedule(NAGINATOR_NUMBER_OF_RETRIES)
        			regexpForRerun("")
        			checkRegexp(false)
        			noChildStrategy("DontRun")
        			delay {
        				fixedDelay {
							delay(NAGINATOR_WAIT_TIME)
						}
        			}
        		}
			}
		}
		logRotator {
			numToKeep(100)
		}
	}
}

println "\nBuilding Cluster run matrices"
println "------------------------"

clusterMatrices.each { jobName, suiteId, markets, githubDetails, branchName, hasTrigger, triggerTime, useLogToTestRail, combinationsCondition, testCases, categoryFilter, deviceScript, retryFailedRuns, emailAfterRun, useTunnel ->
	matrixJob(jobName) {
		combinationFilter(combinationsCondition)
		axes {
	        text('market', markets as String[])
	        text('device', ["IOS", "ANDROID"])
	        text('test', testCases.keySet() as String[])
	    }
	    parameters {
	    	booleanParam('logResultsToTestRail', true, 'Whether or not to log results to TestRail.')
			stringParam('runDateStamp', '', 'PLEASE SET WHEN DOING A MANUAL BUILD')
			stringParam('branch', branchName, 'The branch of the test project to build')
			booleanParam('debug', false, 'Verbose logging on or off.  This is overridden by the trigger.')
			matrixCombinationsParameterDefinition {
				name('COMBINATIONS')
				description('Choose which combinations to run')
				defaultCombinationFilter('') 
				shortcutList {					
					resultShortcut {
						name('Successful')
						exact(false)
						resultsToCheck(["SUCCESS"])
					}
					
					resultShortcut {
						name('Failed')
						exact(false)
						resultsToCheck(["FAILURE"])
					}
					
					// shortcut links for markets
					/*markets.each {
						def market = it
                        println "market: " + market
						combinationFilterShortcut {
							name(market)
							combinationFilter('market=="' + market + '"')
						}
					}*/
					
					// shortcut links for categories
					/*categoryList.each {
						def category = it
						def filter = categoryCombinationConditions.get(category)
						combinationFilterShortcut {
							name(category)
							combinationFilter(filter)
						}
					}*/
					
					none()
				}
			}
	    }
	    wrappers {
			injectPasswords {
				injectGlobalPasswords()
		  	}
		  	buildUserVars()
			timeout{
		  		noActivity(600)
		  		failBuild()
		  	}
		}
	    scm {
			git {
	  			remote {
	                github(githubDetails.url, 'ssh')
					credentials(githubDetails.deployKey)
        		}
				branches('*/${branch}')
		  	}
		}
	    steps {	        
    		shell('rm -f env.properties')
    		shell(deviceScript)
			shell('''
	        	if [ -n "${NAGINATOR_BUILD_NUMBER}" ] &&  [ -z "${BUILD_USER}" ] 
				then
						echo "\n\n########## (CASE #1) Build started by Naginator after a timer run. Grab the old runDateStamp value to log to the correct test plan on TestRail."
				elif [ -z "${BUILD_USER}" ] 
				then
					echo "\n\n########## (CASE #2) Build has been started by a timer. Store current BUILD_TIMESTAMP to use it for later re-run/matrix-reloaded/Naginator runs."
				    rm -f /var/lib/jenkins/jobs/''' + jobName + '''/build.properties	
				    testRailDateStamp="${BUILD_TIMESTAMP}"
				    echo "testRailDateStamp=$testRailDateStamp" >> /var/lib/jenkins/jobs/''' + jobName + '''/build.properties
				elif [ "${runDateStamp}" != "" ]
				then
				    echo "\n\n########## (CASE #3) Build either started manually by a user or is a Naginator re-run after a manual run (with runDateStamp value). Grab the provided runDateStamp value to log to the correct test plan on TestRail."
				    rm -f /var/lib/jenkins/jobs/''' + jobName + '''/build.properties	
				    testRailDateStamp="${runDateStamp}"
				    echo "testRailDateStamp=$testRailDateStamp" >> /var/lib/jenkins/jobs/''' + jobName + '''/build.properties
				else
					echo "\n\n########## (CASE #4) Build either started manually by a user or is a Naginator re-run after a manual run (without runDateStamp value). Grab the old runDateStamp value to log to the correct test plan on TestRail."
			fi''')
	        environmentVariables {
				propertiesFile('env.properties')
	        }
	        environmentVariables {
	            propertiesFile('/var/lib/jenkins/jobs/' + jobName + '/build.properties')
	        }
    		maven {
				goals('clean')
				goals('compile')
				goals('test')
				injectBuildVariables(false)
				
				property('test','${test}')
				property('market','${market}')
				property('suiteFile','run.xml')
				property('driver','CONFIGURED_DRIVER')
				property('driverConfig','${DRIVER_CONFIG}')
				property('buildId', '81879')
				property('debug','${debug}')
				property('takeScreenshots','true')
				property('runDateStamp', '${BUILD_TIMESTAMP}')
				property('ciJobName', '${BUILD_NUMBER}-${test}-${DRIVER_CONFIG}')
				property('isJenkinsRun', 'true')
				property('skipMysqlLogs', 'true')
				property('runId', '${test}-${market}-${BUILD_TIMESTAMP}')
				
				if(useLogToTestRail){
					property('logResultsToTestRail', 'true')
					property('testRailUserName', '${TESTRAIL_USERNAME}')
					property('testRailPassword', '${TESTRAIL_TOKEN}')
					property('testRailUrl', 'https://appauto.testrail.net')
					property('testRailProjectId', '40')
					property('testRailSuiteId', suiteId)
					property('testRailPlanName', 'Avon ON - ' + jobName + ' - ${testRailDateStamp}')					
					property('testRailRunName', '${market} - ${device}')
				}
				
				property('matrixContainerWorkspace', '/var/lib/jenkins/jobs/' + jobName)
				property('testObjectAppId', 'latest')
				mavenOpts('-Xmx256m -XX:MetaspaceSize=256m')
			}
	    }
	    if(hasTrigger){
		    triggers{
		    	authenticationToken('f8ofjow9epspfo')
				cron(triggerTime)
			}
		}
	    publishers {
    		archiveTestNG("**/testng-results.xml") {
	            showFailedBuildsInTrendGraph()
	            markBuildAsFailureOnFailedConfiguration()
    		}
			
			if (emailAfterRun) {
				mailer('hmcconnell@applause.com nfolayan@applausemail.com', false, true)
			}
			
    		wsCleanup()
    		if(retryFailedRuns){
        		naginatorPublisher {
        			rerunIfUnstable(true)
        			rerunMatrixPart(true)
        			maxSchedule(NAGINATOR_NUMBER_OF_RETRIES)
        			regexpForRerun("")
        			checkRegexp(false)
        			noChildStrategy("DontRun")
        			delay {
        				fixedDelay {
							delay(NAGINATOR_WAIT_TIME)
						}
        			}
        		}
			}
		}
		logRotator {
			numToKeep(100)
		}
	}
}

jobName = DEVELOPMENT_JOBS[0];

matrixJob(DEVELOPMENT_JOBS[0]) {
	axes {
        text('device', DEVICES.deviceName as String[])
    }
    parameters {
		stringParam('branch', 'master', 'The branch of the test project to build')
		stringParam('test', 'LoginTest', 'Name of test class.')
		stringParam('market', 'UnitedKingdom', 'Name of market.')
		matrixCombinationsParameterDefinition {
			name('COMBINATIONS')
			description('Choose which combinations to run')
			defaultCombinationFilter('') 
			shortcutList {					
				resultShortcut {
					name('Successful')
					exact(false)
					resultsToCheck(["SUCCESS"])
				}
				
				resultShortcut {
					name('Failed')
					exact(false)
					resultsToCheck(["FAILURE"])
				}
				
				none()
			}
		}
    }
    wrappers {
		injectPasswords {
			injectGlobalPasswords()
	  	}
	  	buildUserVars()
		timeout{
	  		noActivity(600)
	  		failBuild()
	  	}
	}
    scm {
		git {
  			remote {
                github(AVON_ON_GITHUB.url, 'ssh')
				credentials(AVON_ON_GITHUB.deployKey)
    		}
			branches('*/${branch}')
	  	}
	}
    steps {	        
		environmentVariables {
			DEVICES.each {
            	env(it.deviceName, it.configName)
            }
        }
        
		shell('rm -f env.properties')
        shell('''#!/bin/bash
                DRIVER_CONFIG=${!device}
                
				echo "DRIVER_CONFIG=$DRIVER_CONFIG" >> env.properties
                if [[ $DRIVER_CONFIG == *"Android"* ]]; then
	              echo "TEST_OBJECT_TOKEN=$TEST_OBJECT_TOKEN_ANDROID" >> env.properties
	            else
	              echo "TEST_OBJECT_TOKEN=$TEST_OBJECT_TOKEN_IOS" >> env.properties
	            fi''')
        environmentVariables {
			propertiesFile('env.properties')
        }
		maven {
			goals('clean')
			goals('compile')
			goals('test')
			injectBuildVariables(false)
				
			property('test','${test}')
			property('market','${market}')
			property('suiteFile','run.xml')
			property('driver','CONFIGURED_DRIVER')
			property('driverConfig','${DRIVER_CONFIG}')
			property('buildId', '81879')
			property('debug','false')
			property('takeScreenshots','true')
			property('runDateStamp', 'test')
			property('ciJobName', '${test}-${device}-${BUILD_NUMBER}')
			property('isJenkinsRun', 'true')
			property('skipMysqlLogs', 'true')
			property('runId', '${test}-${device}-${BUILD_TIMESTAMP}')
			property('logResultsToTestRail', 'false')	
			property('matrixContainerWorkspace', '/var/lib/jenkins/jobs/' + jobName)
			property('testObjectAppId', 'latest')
			mavenOpts('-Xmx256m -XX:MetaspaceSize=256m')
		}
    }
    
    publishers {
		archiveTestNG("**/testng-results.xml") {
            showFailedBuildsInTrendGraph()
            markBuildAsFailureOnFailedConfiguration()
		}
		
		wsCleanup()
	}
	logRotator {
		numToKeep(20)
	}
}

// ********************************************************* LIST VIEWS ********************************************************* //

listViews.each { listViewName, listViewDescription, listViewJobNames -> 
	listView(listViewName) {
	    description(listViewDescription)
	    filterBuildQueue()
	    filterExecutors()
	    jobs{
	    		names(listViewJobNames)
	    }
	    columns {
			status()
			name()
			lastSuccess()
			lastFailure()
			lastDuration()
			buildButton()
		}
	}
}