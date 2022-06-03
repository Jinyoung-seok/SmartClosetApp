package com.example.smartcloset.Add

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import com.example.smartcloset.R
import kotlinx.android.synthetic.main.addclothes.*

class AddClothes : AppCompatActivity() {

    val PERMISSION_Album = 101 // 앨범 권한 처리
    val REQUEST_STORAGE = 1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addclothes)

        // 앨범 버튼 클릭 리스너 구현
        val albumBtn = findViewById<Button>(R.id.album_addclothes) as Button
        albumBtn.setOnClickListener{
            requirePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_Album)
        }


        camera_addclothes.setOnClickListener{
            Toast.makeText(this,"카메라 버튼입니다", Toast.LENGTH_SHORT).show()
        }
//        album_addclothes.setOnClickListener{
//            Toast.makeText(this,"앨범 버튼입니다",Toast.LENGTH_SHORT).show()
//        }
        cancel_addclothes.setOnClickListener{
            Toast.makeText(this,"취소 버튼입니다",Toast.LENGTH_SHORT).show()
        }
        save_addclothes.setOnClickListener{
            Toast.makeText(this,"저장 버튼입니다",Toast.LENGTH_SHORT).show()
        }
        val myadapter = ArrayAdapter.createFromResource(this, R.array.tagdata1, android.R.layout.simple_spinner_item)
//        val autoAdapter = ArrayAdapter.createFromResource(this,R.array.tagdata1,android.R.layout.simple_spinner_item)

        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        tag1.adapter =myadapter

        tag1.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                tag1txt.text = (view as TextView).text
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
//                result.text = "선택된 가수가 없습니다."
            }

        }

    }

    /**자식 액티비티에서 권한 요청 시 직접 호출하는 메서드
     * @param permissions 권한 처리를 할 권한 목록
     * @param requestCode 권한을 요청한 주체가 어떤 것인지 구분하기 위함.
     * */
    fun requirePermissions(permissions: Array<String>, requestCode: Int) {
//        Logger.d("권한 요청")
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            permissionGranted(requestCode)
        } else {
            // isAllPermissionsGranted : 권한이 모두 승인 되었는지 여부 저장
            // all 메서드를 사용하면 배열 속에 들어 있는 모든 값을 체크할 수 있다.
            val isAllPermissionsGranted =
                permissions.all { checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED }
            if (isAllPermissionsGranted) {
                permissionGranted(requestCode)
            } else {
                // 사용자에 권한 승인 요청
                ActivityCompat.requestPermissions(this, permissions, requestCode)
            }
        }
    }




    /** 사용자가 권한을 승인하거나 거부한 다음에 호출되는 메서드
     * @param requestCode 요청한 주체를 확인하는 코드
     * @param permissions 요청한 권한 목록
     * @param grantResults 권한 목록에 대한 승인/미승인 값, 권한 목록의 개수와 같은 수의 결괏값이 전달된다.
     * */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
            permissionGranted(requestCode)
        } else {
            permissionDenied(requestCode)
        }
    }

    private fun permissionGranted(requestCode: Int) {
        when (requestCode) {
//            PERMISSION_CAMERA -> openCamera()
            PERMISSION_Album -> openGallery()
        }
    }

    private fun permissionDenied(requestCode: Int) {
        when (requestCode) {
//            PERMISSION_CAMERA -> Toast.makeText(
//                this,
//                "카메라 권한을 승인해야 카메라를 사용할 수 있습니다.",
//                Toast.LENGTH_LONG
//            ).show()

            PERMISSION_Album -> Toast.makeText(
                this,
                "저장소 권한을 승인해야 앨범에서 이미지를 불러올 수 있습니다.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent, REQUEST_STORAGE)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            when (requestCode) {
//                REQUEST_CAMERA -> {
//                    realUri?.let { uri ->
//                        imageView.setImageURI(uri)
//                    }
//                }
                REQUEST_STORAGE -> {
                    data?.data?.let { uri ->
                        imagePreview.setImageURI(uri)
                    }
                }
            }
        }
    }
}