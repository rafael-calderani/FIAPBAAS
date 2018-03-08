package br.com.unknown.firebaseapp.extensions

import android.support.design.widget.TextInputLayout

/**
 * Created by logonrm on 07/03/2018.
 */
fun TextInputLayout.getText(): String {
    return this.editText?.text.toString()
}