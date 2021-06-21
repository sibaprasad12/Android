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
>> If you have not written stopSelf method then service will not stop if you stop the service from activity

