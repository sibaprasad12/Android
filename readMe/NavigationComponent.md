# Navigation Component
- Following are major components:
- Your MainActivity class
- A couple of fragments
- Navigation Graph
- Action
- Destinations
- Pop Up To
- Arguments
- Deep Linking
- Navigation Host Fragment
- Navigation Controller


```
Fragment.findNavController()
View.findNavController()
Activity.findNavController(viewId: Int)
```

## Navigate to Destination with NavController
```
view?.findViewById<Button>(R.id.button).setOnClickListener(View.OnClickListener {
    findNavController().navigate(R.id.action_myHomeFragment_to_mySecondFragment)
})
```
>> https://developer.android.com/guide/navigation/navigation-pass-data#supported_argument_types

```
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/my_nav_graph"
        app:startDestination="@id/launcherNavFragment">

    <fragment
            android:id="@+id/launcherNavFragment"
            android:name="spm.androidworld.all.navigation.LauncherNavFragment"
            android:label="LauncherNavFragment"
            tools:layout="@layout/fragment_nav_launcher">
        <action
                android:id="@+id/action_launcher_to_home"
                app:destination="@id/homeNavFragment" />
    </fragment>

    <fragment
            android:id="@+id/homeNavFragment"
            android:name="spm.androidworld.all.navigation.HomeNavFragment"
            android:label="HomeNavFragment"
            tools:layout="@layout/fragment_home_nav">
        <argument
                android:name="param1"
                android:defaultValue="Value 1"
                app:argType="string"
                app:nullable="true" />
        <argument
                android:name="param2"
                android:defaultValue="Value 2"
                app:argType="string"
                app:nullable="true" />
        <action
                android:id="@+id/action_home_to_lastFragment"
                app:destination="@id/lastNavFragment" />

    </fragment>

    <fragment
            android:id="@+id/lastNavFragment"
            android:name="spm.androidworld.all.navigation.LastNavFragment"
            android:label="LastNavFragment"
            tools:layout="@layout/fragment_nav_last">

        <argument
                android:name="param1"
                android:defaultValue="Value 1"
                app:argType="string"
                app:nullable="true" />
        <argument
                android:name="param2"
                android:defaultValue="Value 2"
                app:argType="string"
                app:nullable="true" />
    </fragment>

</navigation>
```
- How to Pass Data between fragments
```
 btnLogin.setOnClickListener {

            val param1 = etValue1.text.toString()
            val param2 = etValue2.text.toString()

            val action =
                HomeNavFragmentDirections.actionHomeToLastFragment()
            action.param1 = param1
            action.param2 = param2
            findNavController().navigate(action)
            // findNavController().navigate(R.id.action_myHomeFragment_to_mySecondFragment)
        }
```