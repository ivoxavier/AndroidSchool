package com.ivoxavier.tasks.service.constants

/**
 * Constantes usadas na aplicação
 */
class TaskConstants private constructor() {

    //URL
    object URL{
        const val URL_API = "http://www.devmasterteam.com/cursoandroidapi/"
        const val PERSON_SERVICE_LOGIN = "authentication/login"
        const val PERSON_SERVICE_CREATE = "authentication/create"
        const val PRIORITY_SERVICE_LIST = "priority"
    }

    // SharedPreferences
    object SHARED {
        const val TOKEN_KEY = "tokenkey"
        const val PERSON_KEY = "personkey"
        const val PERSON_NAME = "personname"
    }

    // Requisições API
    object HEADER {
        const val TOKEN_KEY = "token"
        const val PERSON_KEY = "personkey"
    }

    object HTTP {
        const val SUCCESS = 200
    }

    object BUNDLE {
        const val TASKID = "taskid"
        const val TASKFILTER = "taskfilter"
    }

    object FILTER {
        const val ALL = 0
        const val NEXT = 1
        const val EXPIRED = 2
    }

}