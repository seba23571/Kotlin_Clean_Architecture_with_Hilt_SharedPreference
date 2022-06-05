package com.supraweb.users.core.util

import okio.IOException


class ApiException(message: String) : IOException(message)
class NoInternetException(message: String) : IOException(message)