package com.aba.core.util

import android.content.Context
import android.content.Intent

/**
 * This is a work around to reference and launch activities from each other
 * without violating dependency rule of modular clean architecture.
 * to accommodate this goal we use string constants of each activity
 * to create the [Intent] object associated to that activity.
 */

private const val PACKAGE_NAME = "com.aba"

/**
 * Create an Intent with [Intent.ACTION_VIEW] to an [AddressableActivity].
 */
fun intentTo(addressableActivity: AddressableActivity): Intent {
    return Intent(Intent.ACTION_VIEW).setClassName(
        PACKAGE_NAME,
        addressableActivity.className)
}

fun intentTo(context: Context, addressableActivity: AddressableActivity): Intent {
    return Intent(Intent.ACTION_VIEW).setClassName(
        context,
        addressableActivity.className)
}

/**
 * An [android.app.Activity] that can be addressed by an intent.
 */
interface AddressableActivity {
    /**
     * The activity class name.
     */
    val className: String
}

/**
 * All addressable activities.
 *
 * Can contain intent extra names or functions associated with the activity creation.
 */
object Activities {

    /**
     * App
     */
    object app {
        object SplashActivity : AddressableActivity {
            override val className = "$PACKAGE_NAME.ui.SplashActivity"
        }
    }



    /**
     * Search
     */

    object Main {
        object MainActivity : AddressableActivity {
            override val className = "$PACKAGE_NAME.search.presentation.MainNavigationActivity"
            const val EXTRA_QUERY = "EXTRA_QUERY"
            const val RESULT_CODE_SAVE = 7
        }
    }


    /**
     * Detail
     */
    object Detail{
        object DetailActivity : AddressableActivity {
            override val className = "$PACKAGE_NAME.detail.ui.DetailActivity"
        }

    }
}
