package com.example.demo

import groovy.transform.Canonical
import groovy.transform.ToString

@Canonical
@ToString(includeNames = true)
class TestMessage {
    String type
    Object payload
}
