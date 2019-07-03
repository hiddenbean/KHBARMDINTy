package com.hiddenbean.android.khbarmdinty.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hiddenbean.android.khbarmdinty.R;
import com.hiddenbean.android.khbarmdinty.SetupLocationManuallyFragment;
import com.hiddenbean.android.khbarmdinty.models.Location;
import com.hiddenbean.android.khbarmdinty.models.TextPost;

import java.util.ArrayList;

public class LocationAdapter extends ArrayAdapter<Location> implements Filterable {

    private Context context;
    private ArrayList<Location> locations;
    ArrayList<Location> filterLocations;
    ValueFilter valueFilter;

    public LocationAdapter(Context context, ArrayList<Location> locations) {
        super(context, 0, locations);
        filterLocations = new ArrayList<>(locations);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Location location = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.location_layout, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.location_text);
        textView.setText(location.getName());

        ImageView imageView = convertView.findViewById(R.id.check_view);
        TextView locationTextView = convertView.findViewById(R.id.location_text);

        if(location.isSelected()) {
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<Location> filterList = new ArrayList<>();
                for (int i = 0; i < filterLocations.size(); i++) {
                    if ((filterLocations.get(i).getName().toLowerCase()).contains(constraint.toString().toLowerCase())) {
                        boolean isSelected = filterLocations.get(i).getName().equals(SetupLocationManuallyFragment.selectedLocationName);
                        Location l = new Location(filterLocations.get(i).getName(), filterLocations.get(i).getLongitude(), filterLocations.get(i).getLatitude(), isSelected);
                        filterList.add(l);
                    }
                }


                results.count = filterList.size();
                results.values = filterList;
            } else {

                for (int i = 0; i< filterLocations.size(); i++ ) {
                    filterLocations.get(i).setIsSelected(false);
                    if(filterLocations.get(i).getName().equals(SetupLocationManuallyFragment.selectedLocationName)) {
                        filterLocations.get(i).setIsSelected(true);
                    }
                }

                results.count = filterLocations.size();
                results.values = filterLocations;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((ArrayList<Location>) results.values);
            notifyDataSetChanged();
        }

    }
}
