package br.g12.cp2.sympsion;

import android.content.Context;
import android.icu.lang.UScript;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class separacaocpfnome extends ArrayAdapter<Usuarios> {

    public separacaocpfnome(Context context, int textViewResourceId, List<Usuarios> participantes) {
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
        Usuarios participantes = super.getItem(position);
        if (participantes != null) {
            ((TextView) v.findViewById(R.id.nome)).setText(participantes.nome);
            ((TextView) v.findViewById(R.id.cpf)).setText(participantes.cpf);
        }
        return v;
    }


}


