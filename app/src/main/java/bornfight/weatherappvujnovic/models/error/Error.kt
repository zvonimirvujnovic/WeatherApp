package bornfight.weatherappvujnovic.models.error

import bornfight.weatherappvujnovic.util.Constants

class Error(private val error: ErrorEnum) {
    fun getMessage(): String {
        return when (error) {
            ErrorEnum.NOT_AUTHORIZED -> Constants.notAuthorized
            ErrorEnum.WRONG_API_REQUEST -> Constants.wrongApiRequest
            ErrorEnum.API_CALLS_LIMIT -> Constants.apiCallsLimit
            ErrorEnum.HTTP -> Constants.httpConnectionTimeout
            ErrorEnum.UNKNOWN -> Constants.unknownError
            ErrorEnum.EMPTY_TEXT -> Constants.emptyEditText
        }
    }
}
