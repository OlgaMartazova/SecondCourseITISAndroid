package com.itis.secondcourseitis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.ERROR
import com.itis.secondcourseitis.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        supportFragmentManager.beginTransaction().run {
            add(R.id.container, HomeFragment())
            commit()
        }
        initListeners()
    }

    private fun initListeners() {
        binding.btnHome.setOnClickListener {
            Log.e("LOG", "btnHome нажата")
            supportFragmentManager.beginTransaction().run {
                setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_left, R.animator.exit_to_right)
                replace(R.id.container, HomeFragment())
                addToBackStack(null)
                commit()
            }
        }

        binding.btnSearch.setOnClickListener {
            Log.e("LOG", "btnSearch нажата")
            supportFragmentManager.beginTransaction().run {
                setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_left, R.animator.exit_to_right)
                replace(R.id.container, SearchFragment())
                addToBackStack(null)
                commit()
            }
        }
        binding.btnAdd.setOnClickListener {
            Log.e("LOG", "btnAdd нажата")
            supportFragmentManager.beginTransaction().run {
                setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_left, R.animator.exit_to_right)
                replace(R.id.container, AddFragment())
                addToBackStack(null)
                commit()
            }
        }
        binding.btnInbox.setOnClickListener {
            Log.e("LOG", "btnInbox нажата")
            supportFragmentManager.beginTransaction().run {
                setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_left, R.animator.exit_to_right)
                replace(R.id.container, MoveFragment())
                addToBackStack(null)
                commit()
            }
        }
        binding.btnMe.setOnClickListener {
            Log.e("LOG", "btnMe нажата")
            supportFragmentManager.beginTransaction().run {
                setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_left, R.animator.exit_to_right)
                replace(R.id.container, ProfileFragment())
                addToBackStack(null)
                commit()
            }
        }
    }
}
