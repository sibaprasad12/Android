# Services
- Service is one of the four major components of Android
- Services are used to perform long running tasks in background
- Service does not have UI.

## Service Implementation
- How to implement a service
- How to start a service
- How to stop a service
- Does service actually run in background ?

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

| VALUE Oe Return in OnStart Command | Auto Restart | Intent |
| ------ | ------ |-------|
| START_STICKY| YES | Null Intent |
| START_NOT_STICKY| NO | With Intent When Started |
| START_REDELEVER_INTENT | YES | INTENT |

## When To use thses
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
- In the source code you can find the example here

