package vn.jp.language.ljp.view.jlpt.grammar_detail;

import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.JlptGrammarDetailEntity;
import vn.jp.language.ljp.utils.Common;
import vn.jp.language.ljp.view.BaseFragment;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.jlpt.grammar_detail.question.JlptQuestionAdapter;

/**
 * Created by Administrator on 7/18/2017.
 */

public class JlptGrammarDetailFragment extends BaseFragment<JlpGrammarDetailActivity> {
    private final String TAG = "JlptGrammarDetailFragment";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

//    JlptQuestionAdapter adapter;

    public int pos;
    public int mondai = 0;

    private JlptGrammarDetailPresenter presenter;

    @Override
    public int getLayout() {
        return R.layout.jlpt_grammar_detail_layout;
    }

    @Override
    public void initView(View root) {
        Common.setupRecyclerView(activity, recyclerView, null);

        presenter = new JlptGrammarDetailPresenter(activity, activity.level, activity.test_date, activity.kind);

        if (activity.items != null && activity.items.size() > 0)
            setData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                activity.setResult(AppCompatActivity.RESULT_OK);
                activity.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setData() {
        if(activity.items.get(pos).mondai != mondai){
            mondai = activity.items.get(pos).mondai;
            activity.setMondai(Constant.MONDAI_TEXT[activity.items.get(pos).mondai]);
        }
        presenter.loadItem(activity.items.get(pos).question_id, new ICallback() {
            @Override
            public void onCallback(Object data) {
                List<JlptGrammarDetailEntity> questions = (List<JlptGrammarDetailEntity>) data;
                JlptQuestionAdapter adapter = new JlptQuestionAdapter(questions, activity.items.get(pos).article);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFail(String err) {

            }
        });

    }

}
