package cn.edu.jnu.booklistmainactivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> mainStringSet;
    Book book1=new Book("软件项目管理案例教程（第4版）", R.drawable.book_2);
    Book book2=new Book("创新工程实践", R.drawable.book_no_name);
    Book book3=new Book("信息安全数学基础（第2版）", R.drawable.book_1);
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerViewMain=findViewById(R.id.recycle_view_books);


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewMain.setLayoutManager(linearLayoutManager);
        mainStringSet = new ArrayList<>();
        for(int i=1;i<100;++i)
        {
            //mainStringSet.add("item "+i);
            if (i%3==0) {
                mainStringSet.add(book2.getTitle());
            }
            else if(i%3==1) {
                mainStringSet.add(book3.getTitle());
            }
            else {
                mainStringSet.add(book1.getTitle());
            }
        }
        MainRecycleViewAdapter mainRecycleViewAdapter= new MainRecycleViewAdapter(mainStringSet);
        recyclerViewMain.setAdapter(mainRecycleViewAdapter);
    }

    public class Book{
        int id;
        String title;
        public int getCoverResourceId(){
            return id;
        }
        public String getTitle(){
            return title;
        }
        Book(String title,int id){
            this.id=id;
            this.title=title;
        }
    }

    public static class MainRecycleViewAdapter extends RecyclerView.Adapter<MainRecycleViewAdapter.ViewHolder> {

        private final ArrayList<String> localDataSet;

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        public static class ViewHolder extends RecyclerView.ViewHolder  {
            private final TextView textView;
            private final ImageView imageViewImage;

            public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View

                imageViewImage = view.findViewById(R.id.image_view_book_cover);
                textView = view.findViewById(R.id.text_view_book_title);
            }

            public TextView getTextView() {
                return textView;
            }

            public ImageView getImageViewImage() {
                return imageViewImage;
            }


        }

        /**
         * Initialize the dataset of the Adapter.
         *
         * @param dataSet String[] containing the data to populate views to be used
         * by RecyclerView.
         */
        public MainRecycleViewAdapter(ArrayList<String> dataSet) {
            localDataSet = dataSet;
        }

        // Create new views (invoked by the layout manager)
        @Override
        @NonNull
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_layout, viewGroup, false);

            return new ViewHolder(view);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            viewHolder.getTextView().setText(localDataSet.get(position));
            switch (position%3){
                case 0:
                    viewHolder.getImageViewImage().setImageResource(R.drawable.book_1);
                    break;
                case 1:
                    viewHolder.getImageViewImage().setImageResource(R.drawable.book_2);
                    break;
                case 2:
                    viewHolder.getImageViewImage().setImageResource(R.drawable.book_no_name);
                    break;
            }

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return localDataSet.size();
        }
    }
}