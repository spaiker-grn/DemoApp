package com.example.usersprofiles.usersprofiles

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.uicommon.mvvm.ImageProvider
import com.example.uicommon.mvvm.adapter.BaseViewHolder
import com.example.uicommon.mvvm.adapter.ItemFingerprint
import com.example.uicommon.mvvm.adapter.ListItem
import com.example.usersprofiles.R
import com.example.usersprofiles.databinding.UserCardListItemBinding
import com.example.usersprofiles.model.UserCardModel
import com.example.usersprofiles.model.WeightUnit

class UserFingerprint(
    private val imageProvider: ImageProvider,
    private val onEditClick: (String) -> Unit
) : ItemFingerprint<UserCardListItemBinding, UserCardModel> {

    override fun isRelativeItem(listItem: ListItem) = listItem is UserCardModel

    override fun getLayoutId() = R.layout.user_card_list_item

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<UserCardListItemBinding, UserCardModel> {
        val binding = UserCardListItemBinding.inflate(layoutInflater, parent, false)
        return PostViewHolder(binding, imageProvider, onEditClick)
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<UserCardModel>() {

        override fun areItemsTheSame(oldItem: UserCardModel, newItem: UserCardModel) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: UserCardModel, newItem: UserCardModel) =
            oldItem == newItem
    }
}

class PostViewHolder(
    binding: UserCardListItemBinding,
    private val imageProvider: ImageProvider,
    val onEditPost: (String) -> Unit
) : BaseViewHolder<UserCardListItemBinding, UserCardModel>(binding) {

    override fun onBind(item: UserCardModel) {
        super.onBind(item)
        with(binding) {
            val context = binding.root.context

            item.weight?.apply {
                when (weightUnit) {
                    WeightUnit.KL -> context.getString(com.example.uicommon.R.string.USER_DOB_KL_WEIGHT_POSTFIX)
                    WeightUnit.LB -> context.getString(com.example.uicommon.R.string.USER_DOB_LB_WEIGHT_POSTFIX)
                }.apply {
                    weightTextView.text = value.toString() + this
                }
            }

            item.birthDate?.apply {
                birthDateTextView.text = DateFormat.getLongDateFormat(context).format(
                    item.birthDate.birthDate
                )
            }

            item.image?.apply {
                userImageView.setImageBitmap(
                    imageProvider.provideImage(
                        imageUri,
                        context.contentResolver
                    )
                )
            }

            editUserButton.setOnClickListener {
                onEditPost(item.userId)
            }
        }
    }
}
