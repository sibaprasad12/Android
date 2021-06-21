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

| VALUE Oe Return in OnStart Command | Auto Restart | Intent
| ------ | ------ |-------|
| Dropbox | [plugins/dropbox/README.md][PlDb] |
| GitHub | [plugins/github/README.md][PlGh] |
| Google Drive | [plugins/googledrive/README.md][PlGd] |
| OneDrive | [plugins/onedrive/README.md][PlOd] |
| Medium | [plugins/medium/README.md][PlMe] |
| Google Analytics | [plugins/googleanalytics/README.md][PlGa] |