# MVI - Model - View - Intent

- It’s similar to other commonly known patterns like MVP or MVVM, but it introduces two new concepts: the intent and the state.
- The intent is an event sent to the ViewModel by the View in order to perform a particular task.
- It can be triggered by the user or by other parts of your app. As a result of that, a new state is set on the ViewModel which in turn updates the user interface.
- In the MVI architecture, the View listens to the state.
- Every time the state changes, the View is notified.
- **intent():** This function takes the input from the user (i.e. UI events, like click events) and translates it to “something” that will be passed as a parameter to model() function. This could be a simple string to set a value of the model to or more complex data structure like an Actions or Commands. Here in this blog post, we will stick with the word Action.
- **model():** The model function takes the output from intent() as input to manipulate the model. The output of this function is a new model (state changed). So it should not update an already existing model. We want immutability! We don’t change an already existing one. We copy the existing one and change the state (and afterward it can not be changed anymore).
This function is the only piece of your code that is allowed to change a Model object. Then this new immutable Model is the output of this function.
- **view():** This method takes the model returned from model() function and gives it as input to the view() function. Then the view simply displays this model somehow.
But what about the cycle, one might ask? This is where reactive programming (RxJava, observer pattern) comes in.

## Advantages of MVI
- Maintaining state is no more a challenge with this architecture, As it focuses mainly on states.
- As it is unidirectional, Data flow can be tracked and predicted easily.
- It ensures thread safety as the state objects are immutable.
- Easy to debug, As we know the state of the object when the error occurred.
- It’s more decoupled as each component fulfills its own responsibility.
- Testing the app also will be easier as we can map the business logic for each state.
## Disadvantages of MVI
- It leads to lots of boilerplate code as we have to maintain a state for each user action.
- As we know it has to create lots of objects for all the states. This makes it too costly for app memory management.
- Handling alert states might be challenging while we handle configuration changes. For example, if there is no internet we will show the snackbar, On configuration change, it shows the snackbar again as its the state of the intent. In terms of usability, this has to be handled.
