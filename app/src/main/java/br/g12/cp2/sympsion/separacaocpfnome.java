package br.g12.cp2.sympsion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class separacaocpfnome extends ArrayAdapter<String> {

    public separacaocpfnome(Context context, int textViewResourceId, List<String> participantes) {
        super(context, textViewResourceId, participantes);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            Context ctx = getContext();
            LayoutInflater vi = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.item_subitem, null);
        }
        String participantes = super.getItem(position);
        if (participantes != null) {
            String[] parts = participantes.split(";");
            String part1 = parts[0]; // 004-
            String part2 = parts[1];
            ((TextView) v.findViewById(R.id.nome)).setText(part1);
            ((TextView) v.findViewById(R.id.cpf)).setText(part2);
        }
        return v;
    }


}


