//package com.badoo.ribs.routing.source.tabcontroller
//
//fun bla() {
//
//    val newState = Unit
//    push(newState)
//}
//
//fun ready() {
//    // #1 onTransitionFinished --> sources.commitNew
//    // OR
//    // #2 onTransitionFinished --> sources.dropOld
//}
//
///**
// * No history
// *   main: A -> B, + pipe: A -- back: 1. main false 2. pipe true --> revert main how?
// *   main: A -> B, + pipe: A -- back: 1. pipe true               --> revert main how?
// *   bs:   A -> B, + pipe: A -- back: 1. main true               --> drop pipe how?
// *
// *   finished A -> B, + pipe: A -- back: 1. main true            --> drop pipe how? commit main how?
// *
// *  pipe: true --> revert main
// *  main: true --> drop pipe
// *  problem: combined source + combined history makes it difficult to revert
// *  problem: what to do when A->B finished?
// *
// *
// */
