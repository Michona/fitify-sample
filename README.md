# Fitify Sample

This is a sample app written as a part a test task for Fitify.

## Architecture

This app follows simple clean architecture with domain and UI layer.
There is a uni-directional data flow utilizing *ViewModels* and *Flows*. It borrows some principles
from functional programming for the data layer. It uses coroutines and latest JetPack libraries.
It uses *Compose* for the UI. It's an offline-first approach with local *Room* database and *
Retrofit* for the network requests.

## Modularization

Since this is a sample app, it's not modularized. But in order for this to be scalable and to comply
with SOLID principles, the app should be modularized. The **feature** modules should be split
according to the package names I've put (i.e the features).
Also the **domain** layer (along with **remote** and **local**) should be in their own modules as
well. I also like to use a **coreUi** module for the common UI logic.

## Navigation

I'm a fan of having navigation logic in the ViewModels. I've exposed a *Channel* of nav events that
is consumed by the Activity and the ViewModels send events to it.
This way, there is no passing callbacks (or NavControllers) to the Composables and the ViewModel has
control of the navigation. This has some drawbacks but I think it's better than alternative.

## About Compose

It's my first take on Compose app and I'm sure there are aspects that could be improved. The more I
looked into it and used it, the better it feels.
Writing UI code becomes efficient and fast.

## Theming

On a larger project, more focus should be put to Theming and Styling. I've used a basic setup here,
but in an app with multiple screens and reusable components, it's worth investing more time into
making a style/theme system that fits. Material Design is a good approach.

## Final thoughts

I would love to talk more about architecture and solutions for different problems of large scale
projects.
I'm sure there are things that are out of the scope of this task. Like using KMP, modularization,
authentication and security, notifications, background tasks (with Workers) and so on.