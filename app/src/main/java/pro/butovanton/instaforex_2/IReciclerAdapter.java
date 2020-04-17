package pro.butovanton.instaforex_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IReciclerAdapter extends RecyclerView.Adapter<IReciclerAdapter.IViewHolder> {

    private Context context;
    private List<Signal> signals;

    public IReciclerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public IViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false);
        return new IViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull IViewHolder holder, int position) {
        Signal signal = signals.get(position);
        holder.textView.setText(signal.pair + " : " + signal.comment);
    }

    void setSignals(List<Signal> signals){
        this.signals = signals;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (signals != null) {
            return signals.size();
        } else {
            return 0;
        }

    }


    public class IViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public IViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
