package com.gao.demo.program.file;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.gao.demo.program.R;
import com.gz.glibrary.file.FileEntity;
import java.io.File;
import java.util.List;

/**
 * Created by gaoqian on 2018/4/8.
 */

public class FileListAdapter extends RecyclerView.Adapter<FileListAdapter.FileListViewHolder> {

  private Context context;
  private List<FileEntity> fileEntities;

  public FileListAdapter(Context context) {
    this.context = context;
  }

  @Override public FileListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_filelist, parent, false);
    FileListViewHolder fileListViewHolder = new FileListViewHolder(view);
    return fileListViewHolder;
  }

  @Override public void onBindViewHolder(FileListViewHolder holder, int position) {
    final FileEntity fileEntity = fileEntities.get(position);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        openDir(new File(fileEntity.getPath()));
      }
    });
    holder.nameTxt.setText(fileEntity.getName()+"\n"+fileEntity.getPath());
  }

  public void setFileEntities(List<FileEntity> fileEntities) {
    if (fileEntities != null && !fileEntities.isEmpty()) {
      this.fileEntities = fileEntities;
      notifyItemRangeChanged(0, this.fileEntities.size());
    }
  }

  @Override public int getItemCount() {
    return fileEntities == null ? 0 : fileEntities.size();
  }

  static class FileListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.item_file_list_icon) ImageView iconImg;
    @BindView(R.id.item_file_list_name) TextView nameTxt;

    public FileListViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  @SuppressLint("WrongConstant") public void openDir(File paramFile) {
    Intent localIntent = new Intent();
    localIntent.setFlags(268435456);
    localIntent.setAction("android.intent.action.VIEW");
    String str = "audio/*";
    localIntent.setDataAndType(Uri.fromFile(paramFile), str);
    context.startActivity(localIntent);
  }
}
