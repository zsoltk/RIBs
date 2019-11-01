# Tutorial #6

## The goal of this tutorial

Advanced routing: more routing actions, back stack, adding a menu.


## Going one level higher

We have this simple tree structure of RIBs so far:

```
GreetingsContainer
├── HelloWorld
└── OptionsSelector
```

And we attached GreetingsContainer directly to our RootActivity.

Let's add more screens! things one level higher, so that we'll have:

```
Root
├──Screen 1: GreetingsContainer
│   ├── HelloWorld
│   └── OptionsSelector
├──Screen 2: TBD later
└──Screen 3: TBD later
```

Also we'll need a menu so that we can switch between these screens.

Some design considerations:
1. `Menu` should always be present on the screen
2. All of `Screen 1/2/3` is constructable by `Root`, but only one of them can be active on the screen at any given time
3. RIBs on the same level should not know anything about siblings to keep them decoupled. In practice this will mean that `Menu` should have no direct knowledge about `Screen 1/2/3`.
4. It will be the responsibility of `Root` to track what's happening in `Menu` and chose to activate `Screen 1/2/3`. This will be very similar to what we did in Tutorial 5, where `GreetingsContainer` got the responsibility of coordinating whether to show `HelloWorld` or `OptionsSelector`.

As a result of this, the hierarchy will look like this:


```
Root
├──Menu (Screen 1/2/3 selection)
├──Screen 1: GreetingsContainer
│      ├── HelloWorld
│      └── OptionsSelector
├──Screen 2: TBD later
└──Screen 3: TBD later
```

## Test your knowledge

Starting out you can find new RIBs in the tutorial package, but they are not connected.

By now you should be able to:
1. Take `Root`, and make it a parent of other RIBs on the DI level (`RootComponent`). Feel free to refresh your knowledge by revisiting previous tutorials about how to do this. Also do not add `HelloWorld` and `OptionsSelector` to `Root`: they will remain as a child of `GreetingsContainer` as shown in the hierarchy above. All levels reference their direct children only.
2. Inject builders for these new RIBs into `RootRouter`.
3. Add configurations representing these RIBs to `RootRouter`.
4. Add an `attach` routing action for each of these configurations in the `resolveConfiguration()` method
5. Change the `initialConfiguration` constructor parameter in `RootRouter` to the configuration representing `GreetingsContainer`.

So at this point `Root` RIB has the possibility to attach any of its direct children.

## How the Back stack works

Let's open `RootInteractor` and add these lines manually:

```kotlin
override fun onAttach(ribLifecycle: Lifecycle) {
    super.onAttach(ribLifecycle)
    router.push(Configuration.Menu)
    router.push(Configuration.GreetingsContainer)
    router.push(Configuration.Screen1)
    router.push(Configuration.Screen2)
}
```

All RIBs start existing with a single element in their routing back stack:

```
[(default configuration)]
```

And all `push` operations add a new element to that back stack:

```
[(default configuration)]
[(default configuration), Menu]
[(default configuration), Menu, GreetingsContainer]
[(default configuration), Menu, GreetingsContainer, Screen1]
[(default configuration), Menu, GreetingsContainer, Screen1, Screen2]
```

A simplified rule of the back stack, is that only the last one is ever active (we'll see Overlays later, which is an exception to this).

RIBs representing configurations in all but the latest positions are still alive, but detached from the screen.

This means in practice that if you launch the app now, it should only show `Screen2` RIB.

## Back pressing and the back stack

By default, back pressing is propagated to the deepest (active) levels of the RIB tree by default, where each RIB has a chance to react to it:

1. `Interactor` has a chance to override `handleBackPress()` to do something based on business logic
2. `Router` will be asked if it has back stack to pop. If yes, pop is triggered and nothing else is done.
3. If `Router` had no back stack, the whole thing starts bubbling up the RIB tree to higher levels until someone can handle it. The last fallback is that the hosting `Activity` finishes.
