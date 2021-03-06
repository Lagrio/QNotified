package me.singleneuron.hook

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import me.singleneuron.base.BaseDelayableHookAdapter
import nil.nadph.qnotified.util.LicenseStatus
import nil.nadph.qnotified.util.Utils
import java.lang.Exception

object ForceSystemCamera : BaseDelayableHookAdapter("forceSystemCamera") {
    override fun doInit(): Boolean {
        try {
            val babg = Class.forName("babg")
            XposedHelpers.findAndHookMethod(babg,"a",object : XC_MethodHook(){
                override fun afterHookedMethod(param: MethodHookParam?) {
                    if (LicenseStatus.sDisableCommonHooks) return
                    if (!isEnabled) return
                    Utils.logd("singleneuron babq.a():"+(param!!.result as Boolean))
                    param.result = false
                }
            })
        } catch (e:Exception) {
            Utils.log(e)
            return false
        }
        return true
    }
}