[![](https://github.com/drewhamilton/SkylightAndroidBrand/workflows/CI/badge.svg?branch=main)](https://github.com/drewhamilton/SkylightAndroidBrand/actions?query=workflow%3ACI+branch%3Amain)

# Skylight Android Brand

Comprises branding and theming used for [Skylight](https://github.com/drewhamilton/SkylightAndroid) apps.

## Download
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/dev.drewhamilton.skylight.android.brand/skylight-brand-xml-theme/badge.svg)](https://maven-badges.herokuapp.com/maven-central/dev.drewhamilton.skylight.android.brand/skylight-brand-xml-theme)

Skylight Android is available on Maven Central. It is still in pre-release development, and the API may undergo breaking
changes before version 1.0.0.

```groovy
// Android XML theme:
implementation "drewhamilton.skylight.android.brand:skylight-brand-xml-theme:$version"
```

## Use
Set your app theme to `Theme.Skylight` in your AndroidManifest, and use material components throughout the app for a
fully Skylight-branded application.

```xml
<application
  android:theme="@style/Theme.Skylight" />
```

If you're creating a full-screen app and handling system window insets, use `Theme.Skylight.Fullscreen`, along with the
`?preferredStatusBarColor` and `?preferredNavigationBarColor` attributes where appropriate as system inset backdrops.

## License
```
Copyright 2020 Drew Hamilton

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
