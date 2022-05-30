package kr.ac.cnu.computer.savagr;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class VetFragmentQuestionList extends Fragment {
    Button vet_question;
    VetNavi vetNavi;
    RecyclerView recyclerView;
    RecyclerAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        vetNavi = (VetNavi) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        vetNavi = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_vet_question_list, container, false);


        vet_question = rootView.findViewById(R.id.question);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(vetNavi, LinearLayoutManager.HORIZONTAL, true);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);

        vet_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vetNavi.fragmentChange(2);
            }

        });
        return rootView;

    }

    private void getData() {
        List<String> questionlist_title = Arrays.asList("복숭아(설홍)", "옥수수(백금옥)", "토마토(원예9015)");
        List<String> questionlist_content = Arrays.asList("CNN 모델 분석결과 세균성 반점병으로 추정", "CNN 모델 분석결과 세르코스포라 회색잎 점무늬병으로 추정", "CNN 모델 분석 결과 잎곰팡이병으로 추정");
        List<Integer> questionlist_image = Arrays.asList(R.drawable.questionlist_image1, R.drawable.questionlist_image2, R.drawable.questionlist_image3);
for(int i =0; i<questionlist_title.size(); i++){
    Data data = new Data();
    data.setTitle(questionlist_title.get(i));
    data.setContent(questionlist_content.get(i));
    data.setResId(questionlist_image.get(i));

    adapter.addItem(data);
}

adapter.notifyDataSetChanged();
    }

}