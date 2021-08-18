# Feedback Phoebe
Shake to send feedback for Android



Add Internet permission to your manifest:
```` xml


<uses-permission android:name="android.permission.INTERNET"/>

````


## Dependency Project Level

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:



```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

```

## Dependency App Level

Add dependency in your app module

```gradle
	dependencies {
	        implementation 'com.github.vishalscc:Shake-To-Feedback:1.9'
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

Option 1:  If you don't have another class extended in your activity then extend `FeedbackPhoebeActivity` in Activity which you want to send feedback of that Activity

``` java


	public class YourActivity extends FeedbackPhoebeActivity {

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	    }

	}
	
```

Option 2: if you have your own class extend in your activity then you can extend `FeedbackPhoebeActivity` to that class like this:

```java


	public class YourParentActivity extends FeedbackPhoebeActivity {

	    @Override
	    protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	    }

	}
	

```

Option 3: If you don't want to extend class then you can implement without extending `FeedbackPhoebeActivity` class 

#### Note: FeedbackPhoebe must be register in `onResume()` and must be unregister in `onPause()`


```java


	public class YourActivity extends AppCompatActivity {

	    FeedbackPhoebe feedbackPhoebe;

	    @Override
	    protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		feedbackPhoebe = new FeedbackPhoebe();
		feedbackPhoebe.launch(this);

	    }

	    @Override
	    protected void onResume() {
		super.onResume();
		feedbackPhoebe.register();
	    }

	    @Override
	    protected void onPause() {
		super.onPause();
		feedbackPhoebe.unRegister();
	    }
	}
	

```

## Customization

You can customize color by `FeedbackConfig()` class. Set this class in your `Application` class.

``` java 

	public class YourAppName extends Application {

	    @Override
	    public void onCreate() {
		super.onCreate();

		new FeedbackConfig()
			.setCancelButtonColor(R.color.your_color)
			.setDialogButtonColor(R.color.your_color)
			.setSubmitButtonTextColor(R.color.your_color)
                        .setCancelButtonTextColor(R.color.your_color)
			.setSubmitButtonColor(R.color.your_color)
			.setFontFromAssets("your-font-in-assets.ttf")
			//or
                        .setFontFromResource(R.font.your-font);
			

	    }
	}
	
```




