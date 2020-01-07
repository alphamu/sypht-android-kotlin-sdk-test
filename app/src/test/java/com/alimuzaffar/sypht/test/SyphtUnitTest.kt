package com.alimuzaffar.sypht.test

import android.content.Context
import com.sypht.SyphtClient
import com.sypht.auth.BasicCredentialProvider
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.io.File

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SyphtUnitTest {
    private lateinit var client: SyphtClient
    private lateinit var file: File

    @Before
    fun setup_sypht_client() {
        client = SyphtClient(BasicCredentialProvider(BuildConfig.SYPHT_CLIENT_ID, BuildConfig.SYPHT_CLIENT_SECRET))
        file = getTestFile()
    }

    private fun getTestFile(): File {
        val classLoader = javaClass.classLoader!!
        return File(classLoader.getResource("receipt.pdf").file)
    }

    @Test
    fun upload_sypht_using_file() {
        val id = client.upload(file)
        val result = client.result(id)
        println(result)
        assertNotNull(result)
    }

    @Test
    fun upload_sypht_using_inputstream() {
        val id = client.upload(file.name, file.inputStream())
        val result = client.result(id)
        println(result)
        assertNotNull(result)
    }
}
