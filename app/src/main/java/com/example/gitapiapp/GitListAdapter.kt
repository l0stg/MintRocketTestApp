package com.example.gitapiapp

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import kotlinx.android.synthetic.main.item.view.*


public class GitListAdapter(): RecyclerView.Adapter<GitListAdapter.GitViewHolder>(){
    var gitList= ArrayList<Items>()

    public fun clearData() {
        this.gitList.clear()
        Log.d("ТЭГС", "Очищаем массив")
    }

    fun setData(list: ArrayList<Items>) {
        this.gitList.addAll(list)
    }

    class GitViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val TvRepoName = itemView.tvRepoName
        val TvRepoDesc = itemView.tvRepoDesc
        val TvRepoOwner = itemView.tvRepoOwner
        val TvID = itemView.tvID
        val IvAvatar = itemView.ivAvatar
        val sharebutt = itemView.sharebutt
        val context = itemView.context
        fun bind(data: Items){
            TvRepoName.text = data.name
            TvRepoDesc.text = data.description
            TvID.text = data.id
            TvRepoOwner.text = data.owner.login
            val avatarUrl = data.owner.avatarUrl
            IvAvatar.load(avatarUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_image)
                transformations(CircleCropTransformation())
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitViewHolder {
        return GitViewHolder(LayoutInflater.from(parent.context).
        inflate(R.layout.item,parent,false))
    }

    override fun onBindViewHolder(holder: GitViewHolder, position: Int) {
        holder.bind(gitList[position])
        holder.sharebutt.setOnClickListener(View.OnClickListener {
            var context = holder.context
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT, gitList[position].htmlurl)
            intent.type = "text/plain"
            context.startActivity(Intent.createChooser(intent, "Поделиться"))
        })
        holder.itemView.setOnClickListener(View.OnClickListener {
            var context = holder.context
            val uris = gitList[position].htmlurl
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(uris))
            context.startActivity(intent)
        })
    }
    override fun getItemCount(): Int = gitList.size
}