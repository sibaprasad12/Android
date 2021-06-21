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