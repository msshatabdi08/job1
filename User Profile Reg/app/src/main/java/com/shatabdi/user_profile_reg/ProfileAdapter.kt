package com.shatabdi.user_profile_reg

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shatabdi.user_profile_reg.databinding.ItemProfileBinding

class ProfileAdapter(
    private val onEditClick: (UserProfile) -> Unit,
    private val onDeleteClick: (UserProfile) -> Unit,
    private val onItemClick: (UserProfile) -> Unit
) : ListAdapter<UserProfile, ProfileAdapter.ProfileViewHolder>(ProfileDiffCallback()) {

    inner class ProfileViewHolder(private val binding: ItemProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(profile: UserProfile) {
            binding.tvName.text = profile.name
            binding.tvEmail.text = profile.email
            binding.tvMobile.text = profile.mobile
            binding.tvBirthDate.text = "Birth: ${profile.dateOfBirth}"

            binding.btnEdit.setOnClickListener {
                onEditClick(profile)
            }

            binding.btnDelete.setOnClickListener {
                onDeleteClick(profile)
            }

            binding.root.setOnClickListener {
                onItemClick(profile)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileViewHolder {
        val binding = ItemProfileBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ProfileViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    class ProfileDiffCallback : DiffUtil.ItemCallback<UserProfile>() {
        override fun areItemsTheSame(oldItem: UserProfile, newItem: UserProfile): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserProfile, newItem: UserProfile): Boolean {
            return oldItem == newItem
        }
    }
}