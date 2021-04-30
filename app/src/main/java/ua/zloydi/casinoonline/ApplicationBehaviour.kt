package ua.zloydi.casinoonline

/**
 * A helper class that, depending on the filters, will run all success or
 * failed listeners
 */
class ApplicationBehaviour {
    private val filterList = arrayListOf<() -> Boolean>()
    private val successList = arrayListOf<() -> Unit>()
    private val failedList = arrayListOf<() -> Unit>()

    fun addFilter(filter: () -> Boolean) {
        filterList.add(filter)
    }

    private fun addListener(listener: () -> Unit, list: ArrayList<() -> Unit>) {
        list.add(listener)
    }

    fun addSuccessListener(listener: () -> Unit) = addListener(listener, successList)

    fun addFailedListener(listener: () -> Unit) = addListener(listener, failedList)

    fun removeFilter(filter: () -> Boolean) {
        if (filterList.isNotEmpty()) filterList.remove(filter)
    }

    private fun removeListener(listener: () -> Unit, list: ArrayList<() -> Unit>) {
        if (list.isNotEmpty()) list.remove(listener)
    }

    fun removeSuccessListener(listener: () -> Unit) = removeListener(listener, successList)

    fun removeFailedListener(listener: () -> Unit) = removeListener(listener, failedList)

    //Checks is all filters true or not
    private fun applyFilter(): Boolean {
        for (filter in filterList) {
            if (!filter.invoke()) return false
        }
        return true
    }

    //Runs all listeners
    private fun onResult(list: ArrayList<() -> Unit>) {
        for (listener in list) {
            listener.invoke()
        }
    }

    private fun onSuccess() = onResult(successList)

    private fun onFailed() = onResult(failedList)

    //Depending on filters run success or failed listeners
    fun update() {
        if(applyFilter()){
            onSuccess()
        }else{
            onFailed()
        }
    }
}