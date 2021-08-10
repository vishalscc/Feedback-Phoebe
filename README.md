# Shake-To-Feedback

## Dependency Project Level

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:



```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

```

## Dependency App Level

Add dependency in your app module

```
	dependencies {
	        implementation 'com.github.vishalscc:Shake-To-Feedback:1.1'
	}

```

## Configuration

You can get token from website after sign up, add token in manifest in meta-data like this:

```xml

	 <meta-data
		    android:name="FEEDBACK_PHOEBE_TOKEN"
		    android:value="set-your-token-here" />

```

## Usage

If you don't want modication or you don't have another class extended then extend `BaseActivity` in Activity which you want to send feedback of that Activity

```java


	public class MainActivity extends BaseActivity {

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	    }

	}
	
```

Or if you have another class extend then add this code in that class like this:

```java


	public class BaseActivity extends AppCompatActivity {

	    Shake shake;
	    @Override
	    protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		shake = new Shake();
		shake.launch(this);

	    }

	    @Override
	    protected void onResume() {
		super.onResume();
		shake.register();
	    }

	    @Override
	    protected void onPause() {
		super.onPause();
		shake.unRegister();
	    }
	}
	

```


