# Tutorial #5

## The goal of this tutorial

Understanding how to dynamically switch between child RIBs


## Multi-child RIBs

You may have noticed that there's a new button in the layout: **MORE OPTIONS**. 

You may have also noticed that there's a new RIB in this module: **greetings_picker**. It adds a screen with text options to change the actual greeting we want to show in the Snackbar.

TODO images + generate `OptionSelector` RIB

[TODO]

The idea how it fits in the system:

1. User presses **MORE OPTIONS**, which is beyond the responsibility of `HelloWorld` RIB, so it reports it as `Output`:
2. `GreetingsContainer` catches the output, and being its responsibility to choose what child to display, its routing switches from `HelloWorld` to `OptionsSelector`
3. `OptionsSelector` offers UI interaction, but what should happen when a certain options is selected is beyond its responsibilities, so similarly as before, it reports it as `Output`.
4. `GreetingsContainer` catches the output, switches back to display `HelloWorld` again.
5. The text of the main button in `HelloWorld` should be updated to reflect the newly selected option. This can be done by offering an `Input` to `HelloWorld` which allows setting of the text from outside of the RIB.
6. Pressing the button should also result in the selected text being shown in the Snackbar. This can be done by storing the selected text as part of the state of `GreetingsContainer`.
 
 Because this is a bit too much for one go, we will break it up to parts. In this tutorial, we'll cover reacting points `1-2`, and switching back to `HelloWorld`. 


## Test your knowledge

By now you should be able to:
1. Trigger a new event from the UI that reaches the parent as `Output`
    1. Add a new element to `Output` in `HelloWorld` called `ShowMoreOptions`
    2. Add a new element to `Event` in `HelloWorldView` called `MoreOptionsButtonClicked`
    3. In `HelloWorldView`, find the Button with `R.id.hello_world_more_options`, and set a click listener that will publish `MoreOptionsButtonClicked`
    4. Add the transformation between `Event` and `Output` in the `ViewEventToOutput` transformer
    5. React to this new output in `GreetingsContainerInteractor`. Leave the actual implementation a `TODO()`
2. Add `OptionSelector` RIB as a child of `GreetingsContainer`. This involves:
    1. a new Configuration added to its `GreetingsContainerRouter`
    2. resolving it to an `attach { moreOptionsBuilder.build() }` action
    3. providing `moreOptionsBuilder` to the `GreetingsContainerRouter`
 
For help with the above tasks, you can refer to:
- **tutorial1** / **Further reading** section on how to make a Button trigger an `Output`
- **tutorial2** / **Summary** section on how to add a child RIB to a parent
- **tutorial4** on commnunication with child RIBs, i.e. `Inputs` / `Outputs`


## Implement routing

Now that our new Button can signal the correct `Output`, and now that our container's Router can build the other RIB we need, the only thing we need is to connect the dots.

Business logic triggers Routing:
1. in `GreetingsContainerInteractor` we consume the `Output` of `HelloWorld`
2. in the `when` branch for the new `Output` (where we added a `TODO()`) we want to tell `GreetingsContainerRouter` to switch to the Configuration representing `OptionSelector` RIB.

All we need to do is:

```kotlin
class GreetingsContainerInteractor
    // ...

    internal val helloWorldOutputConsumer: Consumer<HelloWorld.Output> = Consumer {
        when (it) {
            HelloThere -> output.accept(GreetingsSaid("Someone said hello"))
            ShowMoreOptions -> router.push(Configuration.MoreOptions)
        }
    }
}
```

Really, that's it. Try it!

Pressing the **MORE OPTIONS** button the app should display the new screen:

[TODO]


An explanation of what's happening under the hood:
1. `GreetingsContainerInteractor` catches `ShowMoreOptions`
2. the new `MoreOptions` configuration gets pushed to the `GreetingsContainerRouter`'s back stack 
3. it will be automatically resolved to the `attach` routing action we defined
4. as a result, the `OptionSelector` RIB will be built and attached to `GreetingsContainer`
5. the `HelloWorld` RIB will be detached from the screen, so it's no longer visible (worth noting, that it will remain alive and attached to the container, just without its view)


## Pop!
 
Right now the only way of getting back to `HelloWorld` is to press back on the device. This automatically results in:
1. the `MoreOptions` configuration gets popped from the `GreetingsContainerRouter`'s back stack
2. the new last element in the back stack is the `HelloWorld` configuration
3. it gets resolved and as a result, the `HelloWorld` RIB (which was kept alive) gets its view attached back to the screen


## Tutorial complete
 
Congratulations! You can advance to the next one.


## Summary

**Dynamic routing**

1. Make the parent RIB be able to build a child RIB (as seen in **tutorial2**):
    1. Add configuration & routing action in Router
    2. Provide child `Builder` via DI
2. React to some event (usually to child `Output` as seen in **tutorial4**, but can be anything else) in `Interactor` of parent RIB by pushing new configuration to its `Router`
