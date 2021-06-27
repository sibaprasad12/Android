# Data Store

- Preferences DataStore and Proto DataStore
- DataStore provides two different implementations: Preferences DataStore and Proto DataStore.
- **Preferences DataStore** stores and accesses data using keys. This implementation does not require a predefined schema, and it does not provide type safety.
- **Proto DataStore** stores data as instances of a custom data type. This implementation requires you to define a schema using protocol buffers, but it provides type safety.
