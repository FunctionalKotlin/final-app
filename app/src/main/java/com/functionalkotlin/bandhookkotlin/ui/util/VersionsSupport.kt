// Copyright © FunctionalHub.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.util

import android.os.Build

inline fun supportsLollipop(code: () -> Unit) {
    if (Build.VERSION.SDK_INT >= 21) {
        code()
    }
}
