package com.jacob.common.autoservice.eventbus

class LoginEvent {
    var userName: String? = null
    constructor(userName: String?) {
        this.userName = userName
    }
}