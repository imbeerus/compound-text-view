# compound-text-view
[![API](https://img.shields.io/badge/API-15%2B-orange.svg)](https://android-arsenal.com/api?level=15)
[![License](https://img.shields.io/badge/license-Apache%202-red.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![](https://img.shields.io/badge/docs-dokka-blue.svg)](https://github.com/lndmflngs/compound-text-view/blob/master/docs/compound-library/index.md)
![Android CI](https://github.com/lndmflngs/compound-text-view/workflows/Android%20CI/badge.svg?branch=master)
[![](https://jitpack.io/v/lndmflngs/compound-text-view.svg)](https://jitpack.io/#lndmflngs/compound-text-view)

Change gravity of Image Drawable in TextView 🌌

<img src="https://raw.githubusercontent.com/lndmflngs/compound-text-view/master/art/showcase.png?raw=true" width="50%" />

### Features
* Optimized way to use drawables with TextView
* Change gravity of compound drawables
* Drawable transformations (resize, tint)
* CompoundViewClickListener
* Right-to-left Support

## Download
Download the [latest release][1] or grab via Gradle:

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
```
dependencies {
        implementation 'com.github.lndmflngs:compound-text-view:1.2.1'
}
```

## Usage
The simplest way is to use `CompoundTextView` like a normal `TextView`

```xml
<com.lockwood.compound.CompoundTextView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:paddingStart="16dp"
    android:paddingEnd="16dp"
    app:drawableGravity="center"
    app:drawablePadding="16dp"
    app:drawableStart="@drawable/image"
    app:text="Item" />
```

### Loading images
There are several libraries that follow best practices for loading images. You can use these libraries in your app to load images in the most optimized manner. I recommend Glide, but you can use others: Picasso, Coil.

**Examples**: [Glide][5], [Picasso][6], [Coil][7]

**Data binding**: you can use [this][10] adapter

### CompoundTextView Attrs

Attributes | Type | Default | Description
--- | --- | --- | ---
**drawableTint*** | color | - | Tint for all drawables.
**drawableSize*** | dimension | - | Custom size for all drawables.
**drawablePadding*** | dimension | - | Padding for all drawables.
**drawableGravity*** | integer | Gravity.START or Gravity.TOP | Gravity for all drawables in view.
**drawableStart** | reference | - | Drawable to appear to the start of the view.
**drawableTop** | reference | - | Drawable to appear to the top of the view.
**drawableEnd** | reference | - | Drawable to appear to the end of the view.
**drawableBottom** | reference | - | Drawable to appear to the bottom of the view.
**useCustomTransformation** | boolean | false | Use default or custom transformations for drawables before drawable will be wrapped.
**handleClickWithinDrawableBounds** | boolean | false | Handle clicks on "gravity" space or on drawable itself.

*contains individual attr for each position (eg: drawableStartTint, drawableTopTint and etc.)

For more information see [documentation][11]

## Credits
Images provided by [kivnor][8]

## Issue Tracking
Found a bug? Have an idea for an improvement? Feel free to [add an issue](../../issues)

## License

```
Copyright (C) 2020 Ivan Zinovyev (https://github.com/lndmflngs)
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

[1]: https://github.com/lndmflngs/compound-text-view/releases/latest
[2]: https://github.com/bumptech/glide
[3]: https://github.com/square/picasso
[4]: https://github.com/coil-kt/coil/
[5]: https://github.com/lndmflngs/compound-text-view/blob/master/app/src/main/java/com/lockwood/compoundemo/fragment/RecyclerFragment.kt#L102-L107
[6]: https://github.com/lndmflngs/compound-text-view/blob/master/app/src/main/java/com/lockwood/compoundemo/fragment/RecyclerFragment.kt#L110-L114
[7]: https://github.com/lndmflngs/compound-text-view/blob/master/app/src/main/java/com/lockwood/compoundemo/fragment/RecyclerFragment.kt#L117-L124
[8]: https://www.instagram.com/kivnor/
[10]: https://github.com/lndmflngs/compound-text-view/blob/master/app/src/main/java/com/lockwood/compoundemo/BindingExample.kt
[11]: https://github.com/lndmflngs/compound-text-view/blob/master/docs/compound-library/index.md
