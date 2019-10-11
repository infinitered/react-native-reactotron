# react-native-reactotron

## Getting started

`$ npm install react-native-reactotron --save`

### Mostly automatic installation

`$ react-native link react-native-reactotron`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-reactotron` and add `Reactotron.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libReactotron.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainApplication.java`
  - Add `import com.reactlibrary.ReactotronPackage;` to the imports at the top of the file
  - Add `new ReactotronPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-reactotron'
  	project(':react-native-reactotron').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-reactotron/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-reactotron')
  	```


## Usage
```javascript
import Reactotron from 'react-native-reactotron';

// TODO: What to do with the module?
Reactotron;
```
