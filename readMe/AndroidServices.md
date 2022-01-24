# Services

https://android-developers.googleblog.com/2018/10/modern-background-execution-in-android.html
https://sonique6784.medium.com/protect-your-room-database-with-sqlcipher-on-android-78e0681be687

- Service is one of the four major components of Android
- Services are used to perform long running tasks in background
- Service does not have UI.

## Service Implementation
- How to implement a service
- How to start a service
- How to stop a service
- Does service actually run in background ?
- Service run in main thread not in background thread
>> You can not stop service out of the service. But you can call stopSelf() inside onStrtCommand() of service class.
If you have not written stopSelf method then service will not stop if you stop the service from activity

```
if you have not written stopSelf inside onStartCommand of service class
then if you calll stopService(serviceIntent)
It will not stop the service

you can stop the service by calling
stopService(serviceIntent)
```

## Behaviour of Service
- Sometime due to out of memory system kills applications. But services are given higher priority
- Application may gets killed but services run in background
- But in some scenario, services gets killed. what happens to the service when memory available again
- In the onStart command, whatever you return, that depends on the behaviour of the service

| VALUE Of Return in OnStart Command | Auto Restart | Intent |
| ------ | ------ |-------|
| START_STICKY| YES | Null Intent |
| START_NOT_STICKY| NO | With Intent When Started |
| START_REDELEVER_INTENT | YES | INTENT |

## When To use these
- **START_STICKY**
    - Services are being explictly managed & Long Running.
    - No Need to remember state at kill time.
    - Long Running Music Playing Service
- **START_NOT_STICKY**
    - Services are being not explictly managed.
    - Services are periodically running and self stopping
    - Alarm service or server data polling
- **START_REDELEVER_INTENT **
    - Services are being explictly managed.
    - Restart from the previous state at the kill time.
    - File Download

## Bound Service
- When you want to update UI from a service and the values get from a service, then that type of services are known as Bound Services
- If we do the binding of the service to a UI(Fragment/activity). we use **Local binding**
- If you want to bind the UI (Fragment/Activity) of one application to the service of another application, we use **Remote Binding** We can call it as Inter Process Communication
- Boud services are of 2 types
    - Local Binding -> This can be done using IBinder
    - Remote Binding -> This can be done using Messenger Or AIDL

## Local Binding
- In this example we will generate a random number in service and display it in activity
- In th service class we will override onBind method and will return local IBinder
- ```
        class MyBindService : Service() {
            // Random number generator
            private val mGenerator = Random()
            /** method for clients  */
            val randomNumber: Int
                get() = mGenerator.nextInt(100)
            // Binder given to clients
            private val binder = LocalBinder()

            inner class LocalBinder : Binder() {
                // Return this instance of LocalService so clients can call public methods
                fun getService(): MyBindService = this@MyBindService
            }
            override fun onBind(intent: Intent): IBinder {
                return binder
            }
        }
    ```
- You can find the example in sourc ecode **https://github.com/sibaprasad12/Android/tree/main/app/src/main/java/spm/androidworld/all/services/localBindServices**

## Remote Service connection using messenger
- In this case One application can use another application's Service to do it's task.
- In another application the service must be exported = true, otherwise other application can not access the Service
- In the source code you can find the example here **https://github.com/sibaprasad12/Android/tree/main/app/src/main/java/spm/androidworld/all/services/serviceFromCodeTutor*
>> Bound service can not be stopped, it only unbind

## Intent Service
- Service class uses the application’s main thread, while IntentService creates a worker thread and uses that thread to run the service.
- IntentService creates a queue that passes one intent at a time to onHandleIntent(). Thus, implementing a multi-thread should be made by extending Service class directly. Service class needs a manual stop using stopSelf(). Meanwhile, IntentService automatically stops itself when there is no intent in queue.
- IntentService implements onBind() that returns null. This means that the IntentService can not be bound by default.
- IntentService implements onStartCommand() that sends Intent to queue and to onHandleIntent(). In brief, there are only two things to do to use IntentService. 
- Firstly, to implement the constructor. And secondly, to implement onHandleIntent(). For other callback methods, the super is needed to be called so that it can be tracked properly.
- **Thread management:** It automatically processes all incoming requests in a separate thread taking the burden of thread management away from the developer.
- **Request Queue:** It queues up all the incoming requests and they are processed one by one
- **Stop point:** Once the request Queue is empty it automatically stops itself, so the developer need not worry about handling the service lifecycle.

As a developer, we just have to focus on our core business logic and implement it in the OnHandleIntent callback method.
- It's parent class is Service
- But it run in another thread apart from Main thread
- It executes all the methods calling from onHandleIntent method in background thread
- It stop itself once the task is completed
- You can pass multiple parameters to do multiple task in a queue
- You can bind intent service same as we have done for normal service
- When you call intent service like this
```
Intent someIntent1 = new Intent(this, myIntentService.class);
Intent someIntent2 = new Intent(this, myIntentService.class);
Intent someIntent3 = new Intent(this, myIntentService.class);
startService(someIntent1);
startService(someIntent2);
startService(someIntent3);
```
- Then it will not create multiple instance of the Intent service, the same intent service will execute code one by one
- each IntentService only has one HandlerThread that it uses to execute requests in the order that "startService" is called.
- To differentiate between requests, use the Intent system as it's intended! Provide different "Actions" for different jobs the service can carry out, and pass along any extras the IntentService needs to run correctly for that particular job as extras in the Intent object you're using to start the Service.

## Changes Background services in Oreo
- In Oreo and later os, android not allow services to run in the background for longer time
- System kills the services automatically after 2 minutes you put the application in background
- Once you put your application in foreground the service start running again
- To solve this issue, we can use any one of these
- Forground service
- Job Scheduler
- JobIntentService

## JobIntentService
- Its base class is intent service
- You can override all the methods in the service class
- One more method in JobIntentService is enQueueWork method
    - enQueueWork(context:Context, cls: Class, jobId: Int, work:Intent)
    - cls is nothing but the jobIntent service
    - JobId is unique integer
    - work is the intent through which you want to pass data to the service
- One more method is fun onHandleWork(intent: Intent)
    - This method is executed as soon as you invock enqueueWOrk method
- fun onStopCurrentWork() : Boolean
    - If you return true, that means you tell OS to reschedule the service
    - IF you return false, the you tells OS not to reschedule or start the service again
- Don't forget to add permission BIND_JOB_SERVIE
- Also add WAKE_LOCK permission in manifest file
- You can find the example here **https://github.com/AnilDeshpande/UIThreadDemo** in it's respective branch
### Disadvantage of JobIntentService
- You can not put condition to stop a running JobIntentService
- Can not set periodically
- Can not stop explicitly
- Can not put conditin to restart the service
- To handle these issue, we can use JobScheduler
## Job Scheduler with JobService
- This is the base class that handles asynchronous requests that were previously scheduled.
- You are responsible for overriding JobService#onStartJob(JobParameters), which is where you will implement your job logic.
- This service executes each incoming job on a Handler running on your application's main thread.
- This means that you must offload your execution logic to another thread/handler/AsyncTask of your choosing. Not doing so will result in blocking any future callbacks from the JobManager - specifically onStopJob(android.app.job.JobParameters), which is meant to inform you that the scheduling requirements are no longer being met.
- Main methods in Job Service are
    - **jobFinished(JobParameters params, boolean wantsReschedule)**
        - Call this to inform the JobScheduler that the job has finished its work.
    - **abstract boolean	onStartJob(JobParameters params)**
        - Called to indicate that the job has begun executing.
    - **abstract boolean	onStopJob(JobParameters params)**
        - This method is called if the system has determined that you must stop execution of your job even before you've had a chance to call jobFinished(android.app.job.JobParameters, boolean).
- FOr job services, we have to define BIND_JOB_SERVICE in service tag and need to add BOOT_COMPLETED permissin in the manifest to start the jobService once the device rebooted.
- FInd the example **https://github.com/AnilDeshpande/UIThreadDemo/tree/jobscheduler-demo**
- Define your Jobservices like this
```
 ​<service
           ​android:name=".TestJobService"
           ​android:label="Word service"
           ​android:permission="android.permission.BIND_JOB_SERVICE" >

</service
```
## Foreground service
- if the application gets killed then jobIntentService and Job Service gets killed.
- If you want to keep it running if the app killed, then use ForeGroundService.
- Foreground Service performs the task that are noticeable to the user by showing a notification.
- You need to add on permission FOREGROUND_SERVICE in manifest file.
- Define serviceType in the manifest file like (dataSync, DeviceConnection, locationUpdate)
- To start a service you need to call starForeground(id, Notification)

## Work Manager
- An API that makes it easy to schedule deferrable, asynchronous tasks that are expected to run reliably
- Deferrable -
    - Scheduling Mechanism
        - One time
        - Repetitive
        - Compatible with Doze mode , Power saving mode
- Reliabily
    - Run Under Constraints
        - Run only when device is WIFI connected
        - When device idle
        - When it has suffecient Storage
    - Always finish the work
- Worker class
    - doWork()
    - onStopped()
- Constraint
    - Network type
    - BatteryNotLow
    - RequiresCharging
    - DeviceIdle
    - StorageLow
- WorkRequest
    - One time work request
    - Periodic Work Request
- WorkManager
    - Enquue Work
    - Cancelling Work

## Advantage
- You can start your  task from background
- It will continue running after you restart your application
- You can set periodic work as well- which means it will repeate after sometime you set

## Working with multiple Worker
- Lets consider, you have 3 Worker class, Worker1, Worker2, WOrker3. You want to run the task one after another
- WIth the help of WOrkManager
- workManager.toBeginWIth(worker1).then(worker2).then(worker3)
- You can set worker tag in the worker request to cancel later
- If you want to make the worker 1 and worker2 run parlally and worker3 will run later
- You can pass array of worker to workmanager
## How the Workmanager works Long running task using notification
- workmanagerForLongRUnningTAsk.png
- For More details you can find the example here **https://github.com/AnilDeshpande/UIThreadDemo/tree/longrunning-workder-demo**




