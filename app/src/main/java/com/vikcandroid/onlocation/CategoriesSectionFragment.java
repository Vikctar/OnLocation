package com.vikcandroid.onlocation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

 /*
Fragment representing a section of the app
 */
public class CategoriesSectionFragment extends Fragment {
    //public  static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_categories_section, container, false);

        GridView gridView = (GridView) rootView.findViewById(R.id.grid_view);
        gridView.setAdapter(new CatAdapter(rootView.getContext()));

        // Add a click listener (OnItemClickListener) to the gridView items
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CompanyActivity.class).putExtra(Intent.EXTRA_TEXT, position);
                startActivity(intent);
            }
        });




        return rootView;
    }

    private class CatAdapter extends BaseAdapter {
        private List<CatAdapter.Item> items = new ArrayList<>();
        private LayoutInflater mInflater;

        public CatAdapter(Context context) {
            mInflater = LayoutInflater.from(context);

            items.add(new CatAdapter.Item(R.drawable.business_services));
            items.add(new CatAdapter.Item(R.drawable.enterainment_lifestyle));
            items.add(new CatAdapter.Item(R.drawable.shopping));
            items.add(new CatAdapter.Item(R.drawable.food_and_drinks));
            items.add(new CatAdapter.Item(R.drawable.buildings));
            items.add(new CatAdapter.Item(R.drawable.accommodation));
            items.add(new CatAdapter.Item(R.drawable.health));
            items.add(new CatAdapter.Item(R.drawable.financial_services));
            items.add(new CatAdapter.Item(R.drawable.government_institutions));
            items.add(new CatAdapter.Item(R.drawable.properties));
            items.add(new CatAdapter.Item(R.drawable.travel_and_transportation));
            items.add(new CatAdapter.Item(R.drawable.automotive));
            items.add(new CatAdapter.Item(R.drawable.industries));
            items.add(new CatAdapter.Item(R.drawable.public_and_social_services));
            items.add(new CatAdapter.Item(R.drawable.personal_services));
            items.add(new CatAdapter.Item(R.drawable.sports_and_recreation));
            items.add(new CatAdapter.Item(R.drawable.computers_internet));
            items.add(new CatAdapter.Item(R.drawable.hot_deals));
            items.add(new CatAdapter.Item(R.drawable.business_contacts));
            items.add(new CatAdapter.Item(R.drawable.contact_us));

        }




        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return items.get(position).drawableId;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;

            ImageView icon;

            if (convertView == null) {
                view = mInflater.inflate(R.layout.grid_view_item, parent, false);
                view.setTag(R.id.icon, view.findViewById(R.id.icon));

            }
            icon = (ImageView)view.getTag(R.id.icon);

            CatAdapter.Item item = (CatAdapter.Item)getItem(position);

            icon.setImageResource(item.drawableId);

            return view;
        }

        private class Item {
            final int drawableId;

            Item(int drawableId) {
                this.drawableId = drawableId;
            }
        }
    }
}
